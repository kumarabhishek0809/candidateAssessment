package com.assessment.candidate.service;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.entity.CandidateAssessment;
import com.assessment.candidate.entity.QuestionAnswerOption;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.ICandidateRepository;
import com.assessment.candidate.repository.IQuestionAnswerOptionRepository;
import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    private IQuestionAnswerOptionRepository questionAnswerOptionRepository;

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
                    isAssessmentAvailable = candidateAssessments.stream().filter(ca -> ca.isActive())
                            .filter(candidateAssessment
                                    -> candidateAssessment.getAssessment().getId() == assessmentId)
                            .findAny().isPresent();
                }
                if (isAssessmentAvailable) {
                    Optional<Assessment> assessment = assessmentRepository.findById(assessmentId);
                    assessmentDetailResponse.setAssessments(assessment.get());
                }else {
                    assessmentDetailResponse.setDataAvailable(false);
                }
            }
        }
        return assessmentDetailResponse;
    }

    public AssessmentDetailResponse submitAssessment(String emailId,
                                                     SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer) {
        Optional<QuestionAnswerOption> allByAssessment = questionAnswerOptionRepository
                .findAllByAssessmentId(submitAssessmentQuestionAnswer.getAssessmentId());
        System.out.println(allByAssessment.get());
        return null;
    }
}
