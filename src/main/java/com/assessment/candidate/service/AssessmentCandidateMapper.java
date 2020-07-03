package com.assessment.candidate.service;

import com.assessment.candidate.entity.EvaluationQuestionAnswer;
import com.assessment.candidate.entity.Question;
import com.assessment.candidate.model.Assessment;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.IEvaluationQuestionAnswerRepository;
import com.assessment.candidate.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.assessment.candidate.CandidateApplication.CANDIDATE_CACHE;

@Component
public class AssessmentCandidateMapper {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private IAssessmentRepository assessmentRepository;
    @Autowired
    private IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository;

    @Autowired
    private IQuestionRepository questionRepository;


    public Assessment mapEntityToModel(com.assessment.candidate.entity.Assessment assessment) {
        return Assessment.builder()
                .duration(assessment.getDuration())
                .id(assessment.getId())
                .name(assessment.getName())
                .technology(assessment.getTechnology())
                .build();
    }

    @Cacheable(value = CANDIDATE_CACHE, key = " 'getAssessment'+ #assessmentId ")
    public Optional<com.assessment.candidate.entity.Assessment> getAssessment(Integer assessmentId) {
        return assessmentRepository.findById(assessmentId);
    }

    @Cacheable(value = CANDIDATE_CACHE, key = " 'getEvaluationQuestionAnswer' +#assessmentId")
    public Optional<List<EvaluationQuestionAnswer>> getEvaluationQuestionAnswer(
            SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer) {
        Optional<List<EvaluationQuestionAnswer>> questionIn = Optional.of(new ArrayList<>());
        List<SubmitAssessmentQuestionAnswer.QuestionAnswerReq> questionAnswerReq = submitAssessmentQuestionAnswer.getQuestionAnswerReq();
        if (!CollectionUtils.isEmpty(questionAnswerReq)) {
            Iterable<Question> allById = questionRepository.findAllById(questionAnswerReq.stream().map(qa -> qa.getQuestionId())
                    .collect(Collectors.toList()));
            questionIn = evaluationQuestionAnswerRepository.findAllByQuestionIn(allById);
        }
        return questionIn;
    }
}