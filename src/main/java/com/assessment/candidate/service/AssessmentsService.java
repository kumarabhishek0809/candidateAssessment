package com.assessment.candidate.service;

import com.assessment.candidate.entity.*;
import com.assessment.candidate.model.AssessmentCandidateCount;
import com.assessment.candidate.model.AssessmentRequest;
import com.assessment.candidate.model.Email;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.ICandidateAssessmentRepository;
import com.assessment.candidate.repository.ICandidateRepository;
import com.assessment.candidate.repository.IQuestionRepository;
import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentResponse;
import com.assessment.candidate.response.AssessmentSubmittedResponse;
import com.assessment.candidate.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.assessment.candidate.CandidateApplication.CANDIDATE_CACHE;

@Service
public class AssessmentsService {

    @Autowired
    private  AssessmentCandidateMapper assessmentCandidateMapper;
    @Autowired
    private IAssessmentRepository assessmentRepository;
    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private ICandidateAssessmentRepository candidateAssessmentRepository;
    @Autowired
    private IQuestionRepository questionRepository;


    @Value("${adminEmailId}")
    private String adminEmailId;


    @Autowired
    private EmailService emailService;

    @Cacheable(value = CANDIDATE_CACHE, key = " 'getAssessments' ")
    public AssessmentResponse getAssessments() {
        Iterable<Assessment> assessmentRepositoryAll = assessmentRepository.findAll();
        List<com.assessment.candidate.model.Assessment> assessments = new ArrayList<>();
        for (Assessment assessment : assessmentRepositoryAll) {
            assessments.add(assessmentCandidateMapper.mapEntityToModel(assessment));
        }


        AssessmentResponse assessmentResponse = AssessmentResponse.builder()
                .assessments(assessments).build();
        assessmentResponse.setDataAvailable(true);
        return assessmentResponse;
    }

    public AssessmentDetailResponse getAssessment(Integer assessmentId, String emailId) {
        AssessmentDetailResponse assessmentDetailResponse = AssessmentDetailResponse.builder().build();
        assessmentDetailResponse.setDataAvailable(false);
        //validate if test is scheduled for email id.
        boolean isAssessmentAvailable = false;
        if (!StringUtils.isEmpty(emailId)) {
            Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(emailId);
            if (byEmailAddress.isPresent()) {
                Candidate candidate = byEmailAddress.get();
                assessmentDetailResponse.setCandidate(candidateService.mapEntityToModel(candidate, null));
                isAssessmentAvailable = candidate.getCandidateAssessments().stream().filter(ca -> !ca.isStatus())
                        .filter(candidateAssessment
                                -> candidateAssessment.getAssessment().getId() == assessmentId)
                        .findAny().isPresent();
                if (isAssessmentAvailable) {
                    Optional<Assessment> assessment = assessmentCandidateMapper.getAssessment(assessmentId);
                    assessmentDetailResponse.setAssessments(assessment.get());
                    assessmentDetailResponse.setDataAvailable(true);
                } else {
                    assessmentDetailResponse.setDataAvailable(false);
                }
            }
        }
        return assessmentDetailResponse;
    }


    public AssessmentSubmittedResponse submitAssessment(String emailId,
                                                        SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer) throws MessagingException {

        AssessmentSubmittedResponse assessmentDetailResponse = AssessmentSubmittedResponse.builder().build();
        assessmentDetailResponse.setDataAvailable(true);
        Integer totalAssessmentScore = 0;
        Integer totalMarksObtained = 0;
        float totalPercentage = 0;
        Candidate candidateDb = null;
        CandidateAssessment candidateAssessment = null;
        Assessment assessment = null;

        //Validate If candiate has this assessment.
        Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(emailId);
        if (byEmailAddress.isPresent()) {
            candidateDb = byEmailAddress.get();
            mapCandidateFromEntity(assessmentDetailResponse, candidateDb);
            List<CandidateAssessment> dbCandidateAssessments = candidateDb.getCandidateAssessments();
            candidateAssessment = dbCandidateAssessments.stream().filter(ca
                    ->
                    !ca.isStatus()
                    && ca.getAssessment().getId() == submitAssessmentQuestionAnswer.getAssessmentId())
                        .findFirst().orElse(null);
            if (candidateAssessment == null) {
                assessmentDetailResponse.setDataAvailable(false);
                return assessmentDetailResponse;
            }
        }
        //Process Question Answer
        Optional<List<EvaluationQuestionAnswer>> assessmentQueAnsScoreByAssesmentId =
                assessmentCandidateMapper.getEvaluationQuestionAnswer(submitAssessmentQuestionAnswer.getAssessmentId());

        //Calculate How Much Answers were correct.
        List<SubmitAssessmentQuestionAnswer.QuestionAnswerReq> questionAnswersRequestReq = submitAssessmentQuestionAnswer.getQuestionAnswerReq();
        if (assessmentQueAnsScoreByAssesmentId.isPresent()) {
            List<EvaluationQuestionAnswer> evaluationQuestionAnswersDB = assessmentQueAnsScoreByAssesmentId.get();
            totalAssessmentScore = evaluationQuestionAnswersDB.stream()
                    .mapToInt(evaluationQuestionAnswer -> Optional.ofNullable(evaluationQuestionAnswer.getMarks()).orElse(5)).sum();
            for (EvaluationQuestionAnswer evaluationQuestionAnswerDB : evaluationQuestionAnswersDB) {
                Question question = evaluationQuestionAnswerDB.getQuestion();
                if (question != null) {
                    for (SubmitAssessmentQuestionAnswer.QuestionAnswerReq questionAnswerReq : questionAnswersRequestReq) {
                        if (questionAnswerReq.getQuestionId() == evaluationQuestionAnswerDB.getId()) {
                            if (questionAnswerReq.getOptionId() == evaluationQuestionAnswerDB.getOptions().getId()) {
                                totalMarksObtained = totalMarksObtained + Optional.ofNullable(evaluationQuestionAnswerDB.getMarks()).orElse(5);
                                break;
                            }
                        }
                    }
                }
            }
        }
        ///
        if (byEmailAddress.isPresent()) {
            if (candidateAssessment != null) {
                candidateAssessment.setTotalMarksObtained(totalMarksObtained);
                candidateAssessment.setTotalAssessmentScore(totalAssessmentScore);
                if (totalAssessmentScore != 0 && totalMarksObtained != 0) {
                    totalPercentage = (100 * totalMarksObtained) / totalAssessmentScore;
                    String formattedString = String.format("%.02f", totalPercentage);
                    candidateAssessment.setPercentage(formattedString);
                } else {
                    candidateAssessment.setPercentage("" + 0l);
                }
                candidateAssessment.setStatus(Boolean.TRUE);
                candidateAssessment.setResult("Attended");
                candidateAssessment.setAttemptedDate(ZonedDateTime.now());

                assessment = candidateAssessment.getAssessment();
                Integer passingPercentage = Optional.ofNullable(assessment.getPassingPercentage()).orElse(60);
                candidateAssessment.setResult(totalPercentage > passingPercentage ? "Pass" : "Fail");

                candidateAssessment = candidateAssessmentRepository.save(candidateAssessment);
            }
            sendCompletionEmailToAdmin(emailId, candidateDb, candidateAssessment, assessment);
            sendCompletionEmailToCandidate(candidateDb, candidateAssessment, assessment);

        }
        assessmentDetailResponse.setDataSubmited(true);
        return assessmentDetailResponse;
    }

    public void mapCandidateFromEntity(AssessmentSubmittedResponse assessmentDetailResponse, Candidate candidateDb) {
        com.assessment.candidate.model.Candidate candidateRes = com.assessment.candidate.model.Candidate.builder()
                .mobileNo(candidateDb.getMobileNo())
                .countryCode(candidateDb.getCountryCode())
                .dateOfBirth(candidateDb.getDateOfBirth())
                .emailAddress(candidateDb.getEmailAddress())
                .firstName(candidateDb.getFirstName())
                .id(candidateDb.getId())
                .lastName(candidateDb.getLastName())
                .candidateAssessments(null)
                .build();
        assessmentDetailResponse.setCandidate(candidateRes);
    }

    private void sendCompletionEmailToCandidate(Candidate candidateDb,
                                               CandidateAssessment candidateAssessment,
                                               Assessment assessment) throws MessagingException {
        if (candidateDb != null && assessment != null) {
            Email email = Email.builder().subject("Successfully Submitted assessment " + assessment.getName())
                    .message(

                            "<i> Dear  <b>" + candidateDb.getFirstName() + " </b> Greetings!</i><br>" +
                                    "<b> Wish you a nice day! </b> <br> <br>" +
                                    "<h3>" +
                                    "You have successfully submitted your "+ assessment.getName() + " assessment !!!!" + "<br><br>" +
                                    "" +
                                    "" +
                                    "Post evaluation our HR team will get back to you for next steps." + "<br> <br>" +
                                    "</h3>" +
                                    "Regards , <br>" +
                                    "Synechron ")
                    .toEmail(candidateDb.getEmailAddress())
                    .build();
            emailService.sendMail(email);
        }
    }

    private void sendCompletionEmailToAdmin(String emailId, Candidate candidateDb, CandidateAssessment candidateAssessment, Assessment assessment) throws MessagingException {
        //Send Email to admin,
        if (candidateDb != null && emailId != null && assessment != null && candidateAssessment != null) {
            String subject = "Candidate " + Optional.ofNullable(candidateDb.getFirstName()).orElse("") + " " + Optional.ofNullable(candidateDb.getLastName()).orElse("") + " completed Assessement " + assessment.getName();
            Email email = Email.builder()
                    .subject(subject)
                    .message("" +
                            "<i> Dear Team Greetings!</i><br>" +
                            "<b> Wish you a nice day! </b><br> <br> <br>" +
                            "<b><font color=red> " + subject + "</font> </b><br><br><br><br>" +
                            "<style>\n" +
                            "table, th, td {\n" +
                            "  border: 1px solid black;\n" +
                            "}\n" +
                            "</style>" +
                            "<table style=\"width:100%\"> " +
                            "  <tr> " +
                            "    <th>Candidate Name</th> " +
                            "    <th>Candidate Email Id</th> " +
                            "    <th>Assessment Attempted</th> " +
                            "    <th>Score Obtained</th> " +
                            "    <th>Total Score</th> " +
                            "    <th>Percentage</th> " +
                            "    <th>Result</th> " +
                            "  </tr> " +
                            "  <tr> " +
                            "    <td> " + candidateDb.getFirstName() + " " + candidateDb.getLastName() + "</td>" +
                            "    <td> " + emailId + " </td>" +
                            "    <td> " + assessment.getName() + " </td>" +
                            "    <td> " + candidateAssessment.getTotalMarksObtained() + "</td>" +
                            "    <td> " + candidateAssessment.getTotalAssessmentScore() + " </td>" +
                            "    <td> " + candidateAssessment.getPercentage() + " </th> " +
                            "    <td> " + candidateAssessment.getResult() + " </td> " +
                            "  </tr> " +
                            "</table>" +

                            "<br><br><br>" +
                            "Regards ,<br>" +
                            "Synechron ")
                    .toEmail(adminEmailId)
                    .build();
            emailService.sendMail(email);
        }
    }


    @Cacheable(value = CANDIDATE_CACHE, key = " 'candidateAssessmentCount' ")
    public Map<String,AssessmentCandidateCount> candidateAssessmentCount() {
        List<AssessmentCandidateCount> candidateAssessmentCount = candidateAssessmentRepository.getCandidateAssessmentCount();

        Map<String,AssessmentCandidateCount> assessmentCandidateCountMap = new HashMap<>();
        AtomicInteger i = new AtomicInteger(1);
        candidateAssessmentCount.stream().forEach(
                ca -> {
                    assessmentCandidateCountMap.put(""+i,ca);
                    i.incrementAndGet();
                }
        );
        return assessmentCandidateCountMap;
    }

    public GenericResponse addUpdateAssessment(AssessmentRequest assessmentRequest) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(false);
        Assessment assementReq = null;
        if(assessmentRequest != null){
            List<Question> noQuestionAvailable = getNoQuestionAvailable(assessmentRequest);
            if(assessmentRequest.getAssessmentId() == null) {
                assementReq = Assessment.builder()
                        .duration(assessmentRequest.getDuration())
                        .name(assessmentRequest.getName())
                        .passingPercentage(assessmentRequest.getPassingPercentage())
                        .technology(assessmentRequest.getTechnology())
                        .questions(noQuestionAvailable)
                        .build();
            }else {
                assementReq = assessmentRepository.findById(assessmentRequest.getAssessmentId())
                        .orElseThrow( () -> new RuntimeException("Assessment Id Incorrect"));
                List<Question> questions = assementReq.getQuestions();
                if(CollectionUtils.isEmpty(questions)) {
                    questions = new ArrayList<>();
                }
                questions.addAll(noQuestionAvailable);
                assementReq.setQuestions(questions);
            }
            if(assementReq != null) {
                Assessment assessment = assessmentRepository.save(assementReq);
                genericResponse.setDataAvailable(true);
                System.out.println(assessment.getId());
            }
        }

        return genericResponse;
    }

    public List<Question> getNoQuestionAvailable(AssessmentRequest assessmentRequest) {
        return assessmentRequest.getQuestionIds().stream().map(
                qIdReq ->
                        questionRepository.findById(qIdReq).orElseThrow(()
                                -> new RuntimeException("No Question Available"))).collect(Collectors.toList());
    }
}
