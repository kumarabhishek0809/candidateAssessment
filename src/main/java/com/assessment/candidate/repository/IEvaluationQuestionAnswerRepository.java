package com.assessment.candidate.repository;

import com.assessment.candidate.entity.EvaluationQuestionAnswer;
import com.assessment.candidate.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IEvaluationQuestionAnswerRepository extends CrudRepository<EvaluationQuestionAnswer, Integer> {
    Optional<List<EvaluationQuestionAnswer>> findAllByQuestionIn(Iterable<Question> questionIds);
}
