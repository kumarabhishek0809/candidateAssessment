package com.assessment.candidate.repository;

import com.assessment.candidate.entity.QuestionAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IQuestionAnswerOptionRepository extends CrudRepository<QuestionAnswer, Integer> {
    Optional<List<QuestionAnswer>> findAllByAssessmentId(Integer assessmentId);
}
