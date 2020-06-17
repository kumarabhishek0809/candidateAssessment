package com.assessment.candidate.repository;

import com.assessment.candidate.entity.QuestionType;
import org.springframework.data.repository.CrudRepository;

public interface IQuestionTypeRepository extends CrudRepository<QuestionType, Integer> {
}
