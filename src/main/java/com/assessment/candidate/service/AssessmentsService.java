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
                                                     SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer) {

        AssessmentDetailResponse assessmentDetailResponse = AssessmentDetailResponse.builder().build();
        assessmentDetailResponse.setDataAvailable(true);

        float totalAssessmentScore = 0;
        float totalMarksObtained = 0;
        float totalPercentage = 0;
        Candidate candidateEntity = null;
        Assessment assessment = null;

        //Validate If candiate has this assessment.
        Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(emailId);
        if (byEmailAddress.isPresent()) {
            Candidate candidateDb = byEmailAddress.get();
            List<CandidateAssessment> dbCandidateAssessments = candidateDb.getCandidateAssessments();
            long count = dbCandidateAssessments.stream().filter(candidateAssessment
                    -> !candidateAssessment.isStatus() && candidateAssessment.getAssessment().getId() == submitAssessmentQuestionAnswer.getAssessmentId()).count();
            if (count == 0) {
                assessmentDetailResponse.setDataAvailable(false);
                return assessmentDetailResponse;
            }
        }

        //Process Question Answer
        Optional<List<QuestionAnswer>> assessmentQueAnsScoreByAssesmentId = questionAnswerOptionRepository
                .findAllByAssessmentId(submitAssessmentQuestionAnswer.getAssessmentId());

        //Calculate How Much Answers were correct.
        List<SubmitAssessmentQuestionAnswer.QuestionAnswerReq> questionAnswersRequestReq = submitAssessmentQuestionAnswer.getQuestionAnswerReq();
        if (assessmentQueAnsScoreByAssesmentId.isPresent()) {
            List<QuestionAnswer> questionAnswersDB = assessmentQueAnsScoreByAssesmentId.get();
            totalAssessmentScore = questionAnswersDB.stream().mapToLong(questionAnswer -> questionAnswer.getMarks()).sum();
            for (QuestionAnswer questionAnswerDB : questionAnswersDB) {
                Question question = questionAnswerDB.getQuestion();
                if (question != null) {
                    for (SubmitAssessmentQuestionAnswer.QuestionAnswerReq questionAnswerReq : questionAnswersRequestReq) {
                        if (questionAnswerReq.getQuestionId() == questionAnswerDB.getId()) {
                            if (questionAnswerReq.getOptionId() == questionAnswerDB.getOptions().getId()) {
                                totalMarksObtained = totalMarksObtained + questionAnswerDB.getMarks();
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println(assessmentQueAnsScoreByAssesmentId.get());
        }
        ///
        if (byEmailAddress.isPresent()) {
            candidateEntity = byEmailAddress.get();
            Optional<CandidateAssessment> candidateAssessment = candidateEntity.getCandidateAssessments()
                    .stream().filter(ca ->
                            (ca.getAssessment().getId() == submitAssessmentQuestionAnswer.getAssessmentId() && !ca.isStatus())).findFirst();
            if (candidateAssessment.isPresent()) {
                CandidateAssessment candidateAssessment1 = candidateAssessment.get();
                candidateAssessment1.setTotalMarksObtained(totalMarksObtained);
                candidateAssessment1.setTotalAssessmentScore(totalAssessmentScore);
                if (totalAssessmentScore != 0 && totalMarksObtained != 0) {
                    totalPercentage = 100 * (totalMarksObtained / totalAssessmentScore);
                    candidateAssessment1.setPercentage("" + totalPercentage);
                } else {
                    candidateAssessment1.setPercentage("" + 0l);
                }
                candidateAssessment1.setStatus(true);
                candidateAssessment1.setResult("Attended");
                candidateAssessment1.setAttemptedDate(ZonedDateTime.now());
                candidateAssessment1.setStatus(totalPercentage > 60);
                CandidateAssessment canAssessment = candidateAssessmentRepository.save(candidateAssessment1);
            }

            //Send Email,
            if (candidateEntity != null && emailId != null && submitAssessmentQuestionAnswer.getAssessmentId() != null) {
                Email email = Email.builder()
                        .subject("Candidate " + candidateEntity.getFirstName() + " " + candidateEntity.getLastName() + " completed Assessement " + submitAssessmentQuestionAnswer.getAssessmentId())
                        .message("Dear Team" +
                                ",\n \n" +
                                candidateEntity.getFirstName() + " " + candidateEntity.getLastName() + "[ " + emailId + " ] has completed his assessment " +
                                "and scored " + totalMarksObtained + " marks out of " + totalAssessmentScore + " thus percentage is " + totalPercentage +
                                "\n" +
                                ",\n \n" +
                                "Regards ,\n" +
                                ",\n \n" +
                                "Synechron ")
                        .toEmail(adminEmailId)
                        .build();
                emailService.sendMail(email);
            }
        }
        return assessmentDetailResponse;
    }
}
