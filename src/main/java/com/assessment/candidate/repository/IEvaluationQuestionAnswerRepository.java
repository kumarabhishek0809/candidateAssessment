package com.assessment.candidate.repository;

import com.assessment.candidate.entity.EvaluationQuestionAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IEvaluationQuestionAnswerRepository extends CrudRepository<EvaluationQuestionAnswer, Integer> {
    Optional<List<EvaluationQuestionAnswer>> findAllByAssessmentId(Integer assessmentId);
}
