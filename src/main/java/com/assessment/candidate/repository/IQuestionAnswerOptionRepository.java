package com.assessment.candidate.repository;

import com.assessment.candidate.entity.QuestionAnswerOption;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IQuestionAnswerOptionRepository extends CrudRepository<QuestionAnswerOption, Integer> {
    Optional<List<QuestionAnswerOption>> findAllByAssessmentId(Integer assessmentId);
}
