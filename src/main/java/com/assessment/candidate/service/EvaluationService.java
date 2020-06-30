package com.assessment.candidate.service;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.entity.EvaluationQuestionAnswer;
import com.assessment.candidate.entity.Options;
import com.assessment.candidate.entity.Question;
import com.assessment.candidate.model.EvaluationRequest;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.IEvaluationQuestionAnswerRepository;
import com.assessment.candidate.repository.IOptionsRepository;
import com.assessment.candidate.repository.IQuestionRepository;
import com.assessment.candidate.response.EvaluationResponse;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    private final IAssessmentRepository assessmentRepository;
    private final IQuestionRepository questionRepository;
    private final IOptionsRepository optionsRepository;
    private final IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository;

    public EvaluationService(IAssessmentRepository assessmentRepository,
                             IQuestionRepository questionRepository,
                             IOptionsRepository optionsRepository,
                             IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository) {
        this.assessmentRepository = assessmentRepository;
        this.questionRepository = questionRepository;
        this.optionsRepository = optionsRepository;
        this.evaluationQuestionAnswerRepository = evaluationQuestionAnswerRepository;
    }


    public EvaluationResponse addAnswerToEvaluation(EvaluationRequest evaluationRequest) {
        EvaluationResponse evaluationResponse = new EvaluationResponse();
        evaluationResponse.setDataAvailable(false);

        Question question = questionRepository.findById(evaluationRequest.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Invalid Question ID "
                        + evaluationRequest.getQuestionId()));

        Assessment assessment = assessmentRepository.findById(evaluationRequest.getAssessmentId())
                .orElseThrow(() -> new RuntimeException("Invalid Assessment ID "
                        + evaluationRequest.getAssessmentId()));

        Options options = optionsRepository.findById(evaluationRequest.getOptionId())
                .orElseThrow(() -> new RuntimeException("Invalid Options ID "
                        + evaluationRequest.getOptionId()));


        EvaluationQuestionAnswer evaluationQuestionAnswer = new EvaluationQuestionAnswer();
        evaluationQuestionAnswer.setAssessment(assessment);
        evaluationQuestionAnswer.setMarks(evaluationRequest.getMarks());
        evaluationQuestionAnswer.setOptions(options);
        evaluationQuestionAnswer.setQuestion(question);

        EvaluationQuestionAnswer questionAnswer = evaluationQuestionAnswerRepository.save(evaluationQuestionAnswer);
        evaluationResponse.setDataAvailable(true);
        evaluationResponse.setId(questionAnswer.getId());
        evaluationResponse.setMarks(questionAnswer.getMarks());
        evaluationResponse.setOptions(questionAnswer.getOptions());
        evaluationResponse.setQuestion(questionAnswer.getQuestion().getHeader());
        return evaluationResponse;
    }
}
