package com.assessment.candidate.service;

import com.assessment.candidate.entity.*;
import com.assessment.candidate.model.AssessmentCandidateCount;
import com.assessment.candidate.model.AssessmentRequest;
import com.assessment.candidate.model.Email;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.repository.*;
import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentQuestionResponse;
import com.assessment.candidate.response.AssessmentResponse;
import com.assessment.candidate.response.AssessmentSubmittedResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AssessmentCandidateMapper assessmentCandidateMapper;
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
    @Autowired
    private ICandidateAssessmentResultSubmissionRepository candidateAssessmentResultSubmissionRepository;
    @Autowired
    private IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository;
    @Autowired
    private SystemConfigurationService systemConfigurationService;

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
            Candidate candidate = candidateRepository.findByEmailAddress(emailId)
                    .orElseThrow(() ->
                            new RuntimeException("Candidate Details does not exits for email : " + emailId));

            assessmentDetailResponse.setCandidate(candidateService
                    .mapEntityToModel(candidate, null));

            isAssessmentAvailable = candidate.getCandidateAssessments()
                    .stream().filter(ca -> !ca.isStatus())
                    .filter(candidateAssessment
                            -> candidateAssessment.getAssessment().getId().equals(assessmentId))
                    .findAny().isPresent();

            if (isAssessmentAvailable) {
                Assessment assessment = assessmentCandidateMapper.getAssessment(assessmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Assessment not exists for assessmentId "
                                        + assessmentId));

                assessmentDetailResponse.setAssessments(
                        AssessmentDetailResponse.Assessment.builder()
                                .id(assessment.getId())
                                .name(assessment.getName())
                                .duration(assessment.getDuration())
                                .technology(assessment.getTechnology())
                                .passingPercentage(assessment.getPassingPercentage())
                                .questions(getRandomQuestions(assessment))
                                .noOfQuestions(Optional.ofNullable(assessment.getQuestionCount()).orElse(new Integer(25)))
                                .build());
                assessmentDetailResponse.setDataAvailable(true);
            } else {
                assessmentDetailResponse.setDataAvailable(false);
            }
        }
        return assessmentDetailResponse;
    }

    private List<Question> getRandomQuestions(Assessment assessment) {
        List<Question> questions = new ArrayList<>();
        if (assessment != null) {

            List<Question> assessmentQuestions = assessment.getQuestions().stream().filter(question -> question.isValid()).collect(Collectors.toList());
            Collections.shuffle(assessmentQuestions);

            Integer questionCount =
                    Optional.ofNullable(assessment.getQuestionCount())
                            .orElse(25);
            if (questionCount > assessmentQuestions.size()) {
                questionCount = assessmentQuestions.size();
            }

            questions = assessmentQuestions.subList(0, questionCount);
            for (Question question : questions) {
                Collections.shuffle(question.getOptions());
            }
        }
        return questions;
    }


    public AssessmentSubmittedResponse submitAssessment(String emailId,
                                                        SubmitAssessmentQuestionAnswer
                                                                submitAssessmentQuestionAnswer)
            throws MessagingException {

        AssessmentSubmittedResponse assessmentDetailResponse =
                AssessmentSubmittedResponse.builder().build();
        assessmentDetailResponse.setDataAvailable(true);

        Integer totalAssessmentScore = 0;
        Integer totalMarksObtained = 0;
        float totalPercentage = 0;
        Candidate candidateDb = null;
        CandidateAssessment candidateAssessment = null;
        Assessment assessment = null;

        //Validate If candidate has this assessment.
        candidateDb = candidateRepository
                .findByEmailAddress(emailId)
                .orElseThrow(() -> new RuntimeException("Candidate Not Found with email id " + emailId));
        mapCandidateFromEntity(assessmentDetailResponse, candidateDb);

        List<CandidateAssessment> dbCandidateAssessments = candidateDb.getCandidateAssessments();
        candidateAssessment = dbCandidateAssessments.stream().filter(ca
                ->
                !ca.isStatus()
                        && ca.getAssessment().getId().equals(submitAssessmentQuestionAnswer.getAssessmentId()))
                .findFirst().orElse(null);

        if (candidateAssessment == null) {
            assessmentDetailResponse.setDataAvailable(false);
            return assessmentDetailResponse;
        }

        //Process Question Answer
        List<EvaluationQuestionAnswer> evaluationQuestionAnswersDB =
                assessmentCandidateMapper
                        .getEvaluationQuestionAnswer(submitAssessmentQuestionAnswer).get();

        //Calculate How many Answers were correct.
        List<SubmitAssessmentQuestionAnswer.QuestionAnswerReq> questionAnswersRequestReq =
                submitAssessmentQuestionAnswer.getQuestionAnswerReq();

        for (EvaluationQuestionAnswer evaluationQuestionAnswerDB : evaluationQuestionAnswersDB) {
            if (!CollectionUtils.isEmpty(questionAnswersRequestReq)) {
                for (SubmitAssessmentQuestionAnswer.QuestionAnswerReq questionAnswerReq :
                        questionAnswersRequestReq) {
                    if (questionAnswerReq.getQuestionId().equals(evaluationQuestionAnswerDB.getQuestion()
                            .getId())) {
                        if (questionAnswerReq.getOptionId().equals(evaluationQuestionAnswerDB.getOptions()
                                .getId())) {
                            totalMarksObtained =
                                    totalMarksObtained + Optional.ofNullable(evaluationQuestionAnswerDB.getMarks()).orElse(5);
                            break;
                        }
                    }
                }
            }
        }

        if (candidateAssessment != null) {
            assessment = candidateAssessment.getAssessment();
            Integer questionCount = assessment.getQuestionCount();
            totalAssessmentScore = questionCount * 5;

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

            Integer passingPercentage = Optional.ofNullable(assessment.getPassingPercentage()).orElse(59);
            candidateAssessment.setResult(totalPercentage >= passingPercentage ? "Pass" : "Fail");

            populateAssessmentResultSubmission(candidateAssessment, submitAssessmentQuestionAnswer);

            candidateAssessment = candidateAssessmentRepository.save(candidateAssessment);
            sendCompletionEmailToAdmin(emailId, candidateDb, candidateAssessment, assessment);
            sendCompletionEmailToCandidate(candidateDb, candidateAssessment, assessment);

            assessmentDetailResponse.setDataSubmited(true);
        }
        return assessmentDetailResponse;
    }

    private void populateAssessmentResultSubmission(CandidateAssessment candidateAssessment,
                                                    SubmitAssessmentQuestionAnswer
                                                            submitAssessmentQuestionAnswer) {

        if (submitAssessmentQuestionAnswer != null && !CollectionUtils.isEmpty(submitAssessmentQuestionAnswer.getQuestionAnswerReq())) {
            List<SubmitAssessmentQuestionAnswer.QuestionAnswerReq> questionAnswerReq =
                    submitAssessmentQuestionAnswer.getQuestionAnswerReq();


            List<CandidateAssessmentResultSubmission> candidateAssessmentResultSubmissions = questionAnswerReq.stream().map(qaR ->
                    CandidateAssessmentResultSubmission.builder()
                            .candidateAssessment(candidateAssessment)
                            .optionId(qaR.getOptionId())
                            .questionId(qaR.getQuestionId())
                            .build()).collect(Collectors.toList());
            candidateAssessmentResultSubmissionRepository.saveAll(candidateAssessmentResultSubmissions);
        }

    }

    public void mapCandidateFromEntity(AssessmentSubmittedResponse
                                               assessmentDetailResponse,
                                       Candidate candidateDb) {
        com.assessment.candidate.model.Candidate candidateRes = com.assessment.candidate.model.Candidate.builder()
                .mobileNo(candidateDb.getMobileNo())
                .countryCode(candidateDb.getCountryCode())
                .dateOfBirth(candidateDb.getDateOfBirth())
                .emailAddress(candidateDb.getEmailAddress())
                .firstName(candidateDb.getFirstName())
                .id(candidateDb.getId())
                .lastName(Optional.ofNullable(candidateDb.getLastName()).orElse(""))
                .candidateAssessments(null)
                .build();
        assessmentDetailResponse.setCandidate(candidateRes);
    }

    private void sendCompletionEmailToCandidate(Candidate candidateDb,
                                                CandidateAssessment candidateAssessment,
                                                Assessment assessment) throws MessagingException {
        List<String> toEmails = new ArrayList<>();
        toEmails.add(candidateDb.getEmailAddress());
        if (candidateDb != null && assessment != null) {
            Email email = Email.builder().subject("Successfully Submitted assessment " + assessment.getName())
                    .message(

                            "<i> Dear  <b>" + candidateDb.getFirstName() + " </b> Greetings!</i><br>" +
                                    "<b> Wish you a nice day! </b> <br> <br>" +
                                    "<h3>" +
                                    "You have successfully submitted your " + assessment.getName() + " assessment !!!!" + "<br><br>" +
                                    "" +
                                    "" +
                                    "Post evaluation our HR team will get back to you for next steps." + "<br> <br>" +
                                    "</h3>" +
                                    "Regards , <br>" +
                                    "Synechron ")
                    .toEmail(toEmails)
                    .build();
            emailService.sendMail(email);
        }
    }

    private void sendCompletionEmailToAdmin(String emailId, Candidate candidateDb, CandidateAssessment
            candidateAssessment, Assessment assessment) throws MessagingException {
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
                            "    <td> " + candidateDb.getFirstName() + " " + Optional.ofNullable(candidateDb.getLastName()).orElse("") + "</td>" +
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
                    .toEmail(systemConfigurationService.adminEmails())
                    .build();
            emailService.sendMail(email);
        }
    }

    @Cacheable(value = CANDIDATE_CACHE, key = " 'candidatesAssessments' ")
    public List<AssessmentCandidateCount> candidatesAssessments() {
        return candidateAssessmentRepository.getCandidateAssessmentCount();
    }


    @Cacheable(value = CANDIDATE_CACHE, key = " 'candidateAssessmentCount' ")
    public Map<String, AssessmentCandidateCount> candidateAssessmentCount() {
        List<AssessmentCandidateCount> candidateAssessmentCount = candidateAssessmentRepository.getCandidateAssessmentCount();

        Map<String, AssessmentCandidateCount> assessmentCandidateCountMap = new HashMap<>();
        AtomicInteger i = new AtomicInteger(1);
        candidateAssessmentCount.stream().forEach(
                ca -> {
                    assessmentCandidateCountMap.put("" + i, ca);
                    i.incrementAndGet();
                }
        );
        return assessmentCandidateCountMap;
    }

    public AssessmentQuestionResponse addUpdateAssessment(AssessmentRequest assessmentRequest) {
        AssessmentQuestionResponse genericResponse = new AssessmentQuestionResponse();
        genericResponse.setDataAvailable(false);
        Assessment assementReq = null;
        if (assessmentRequest != null) {
            List<Question> noQuestionAvailable = getNoQuestionAvailable(assessmentRequest);
            if (assessmentRequest.getAssessmentId() == null) {
                assementReq = Assessment.builder()
                        .duration(assessmentRequest.getDuration())
                        .name(assessmentRequest.getName())
                        .passingPercentage(assessmentRequest.getPassingPercentage())
                        .technology(assessmentRequest.getTechnology())
                        .questions(noQuestionAvailable)
                        .build();
            } else {
                assementReq = assessmentRepository.findById(assessmentRequest.getAssessmentId())
                        .orElseThrow(() -> new RuntimeException("Assessment Id Incorrect"));
                List<Question> questions = assementReq.getQuestions();
                if (CollectionUtils.isEmpty(questions)) {
                    questions = new ArrayList<>();
                }
                questions.addAll(noQuestionAvailable);
                assementReq.setQuestions(questions);
            }
            if (assementReq != null) {
                Assessment assessment = assessmentRepository.save(assementReq);
                genericResponse.setDataAvailable(true);
                genericResponse.setAssessment(assessment);
                System.out.println(assessment.getId());
            }
        }

        return genericResponse;
    }

    public List<Question> getNoQuestionAvailable(AssessmentRequest assessmentRequest) {
        List<Integer> questionIds = assessmentRequest.getQuestionIds();
        List<Question> noQuestionAvailable = null;

        if (!CollectionUtils.isEmpty(questionIds)) {
            noQuestionAvailable = questionIds.stream().map(
                    qIdReq ->
                            questionRepository.findById(qIdReq).orElseThrow(()
                                    -> new RuntimeException("No Question Available WITH ID" + qIdReq)))
                    .collect(Collectors.toList());
        }
        return noQuestionAvailable;
    }
}
