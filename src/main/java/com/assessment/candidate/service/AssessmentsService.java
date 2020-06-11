package com.assessment.candidate.service;

import com.assessment.candidate.entity.*;
import com.assessment.candidate.model.Email;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.ICandidateAssessmentRepository;
import com.assessment.candidate.repository.ICandidateRepository;
import com.assessment.candidate.repository.IQuestionAnswerOptionRepository;
import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentsService {

    @Autowired
    private IAssessmentRepository assessmentRepository;
    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private ICandidateAssessmentRepository candidateAssessmentRepository;
    @Autowired
    private IQuestionAnswerOptionRepository questionAnswerOptionRepository;

    @Value("${adminEmailId}")
    private String adminEmailId;


    @Autowired
    private EmailService emailService;

    public AssessmentResponse getAssessments() {
        Iterable<Assessment> assessmentRepositoryAll = assessmentRepository.findAll();
        List<com.assessment.candidate.model.Assessment> assessments = new ArrayList<>();
        for (Assessment assessment : assessmentRepositoryAll) {
            assessments.add(mapEntityToModel(assessment));
        }


        AssessmentResponse assessmentResponse = AssessmentResponse.builder()
                .assessments(assessments).build();
        assessmentResponse.setDataAvailable(true);
        return assessmentResponse;
    }


    public com.assessment.candidate.model.Assessment mapEntityToModel(Assessment assessment) {
        return com.assessment.candidate.model.Assessment.builder()
                .duration(assessment.getDuration())
                .id(assessment.getId())
                .name(assessment.getName())
                .technology(assessment.getTechnology())
                .build();
    }

    public AssessmentDetailResponse getAssessment(Integer assessmentId, String emailId) {
        AssessmentDetailResponse assessmentDetailResponse = AssessmentDetailResponse.builder().build();
        assessmentDetailResponse.setDataAvailable(true);
        //validate if test is scheduled for email id.
        boolean isAssessmentAvailable = false;
        if (!StringUtils.isEmpty(emailId)) {
            Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(emailId);
            if (byEmailAddress.isPresent()) {
                Candidate candidate = byEmailAddress.get();
                assessmentDetailResponse.setCandidate(candidateService.mapEntityToModel(candidate, null));
                List<CandidateAssessment> candidateAssessments = candidate.getCandidateAssessments();
                if (!CollectionUtils.isEmpty(candidateAssessments)) {
                    isAssessmentAvailable = candidateAssessments.stream().filter(ca -> !ca.isStatus())
                            .filter(candidateAssessment
                                    -> candidateAssessment.getAssessment().getId() == assessmentId)
                            .findAny().isPresent();
                }
                if (isAssessmentAvailable) {
                    Optional<Assessment> assessment = assessmentRepository.findById(assessmentId);
                    assessmentDetailResponse.setAssessments(assessment.get());
                } else {
                    assessmentDetailResponse.setDataAvailable(false);
                }
            }
        }
        return assessmentDetailResponse;
    }

    public AssessmentDetailResponse submitAssessment(String emailId,
                                                     SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer) throws MessagingException {

        AssessmentDetailResponse assessmentDetailResponse = AssessmentDetailResponse.builder().build();
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
        Optional<List<EvaluationQuestionAnswer>> assessmentQueAnsScoreByAssesmentId = questionAnswerOptionRepository
                .findAllByAssessmentId(submitAssessmentQuestionAnswer.getAssessmentId());

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
                                totalMarksObtained = totalMarksObtained + evaluationQuestionAnswerDB.getMarks();
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
                    totalPercentage = 100 * (totalMarksObtained / totalAssessmentScore);
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

                CandidateAssessment canAssessment = candidateAssessmentRepository.save(candidateAssessment);
            }

            //Send Email,
            if (candidateDb != null && emailId != null && assessment != null && candidateAssessment != null) {
                String subject = "Candidate " + candidateDb.getFirstName() + " " + candidateDb.getLastName() + " completed Assessement " + assessment.getName();
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
        return assessmentDetailResponse;
    }
}
