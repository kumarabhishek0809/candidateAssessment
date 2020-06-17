package com.assessment.candidate.repository;

import com.assessment.candidate.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface IQuestionRepository extends CrudRepository<Question, Integer> {
}
