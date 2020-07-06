package com.assessment.candidate.repository;

import com.assessment.candidate.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IQuestionRepository extends CrudRepository<Question, Integer> {
    Optional<Question> findByHeader(String trimWhitespace);
}
