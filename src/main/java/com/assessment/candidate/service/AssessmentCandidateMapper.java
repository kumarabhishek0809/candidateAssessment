package com.assessment.candidate.service;

import com.assessment.candidate.entity.EvaluationQuestionAnswer;
import com.assessment.candidate.model.Assessment;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.IEvaluationQuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.assessment.candidate.CandidateApplication.CANDIDATE_CACHE;

@Component
public class AssessmentCandidateMapper {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private IAssessmentRepository assessmentRepository;
    @Autowired
    private IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository;


    public Assessment mapEntityToModel(com.assessment.candidate.entity.Assessment assessment) {
        return Assessment.builder()
                .duration(assessment.getDuration())
                .id(assessment.getId())
                .name(assessment.getName())
                .technology(assessment.getTechnology())
                .build();
    }

    @Cacheable(value = CANDIDATE_CACHE, key = " 'getAssessment'+ #assessmentId ")
    public Optional<com.assessment.candidate.entity.Assessment> getAssessment(Integer assessmentId){
        cacheManager.getCache(CANDIDATE_CACHE);
        Optional<com.assessment.candidate.entity.Assessment> byId = assessmentRepository.findById(assessmentId);
        cacheManager.getCache(CANDIDATE_CACHE);
        return byId;
    }

    @Cacheable(value = CANDIDATE_CACHE,key = " 'getEvaluationQuestionAnswer' +#assessmentId")
    public Optional<List<EvaluationQuestionAnswer>> getEvaluationQuestionAnswer(Integer assessmentId) {
        return evaluationQuestionAnswerRepository
                .findAllByAssessmentId(assessmentId);
    }
}