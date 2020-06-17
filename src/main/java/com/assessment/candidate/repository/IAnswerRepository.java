package com.assessment.candidate.repository;

import com.assessment.candidate.entity.Answer;
import org.springframework.data.repository.CrudRepository;

public interface IAnswerRepository extends CrudRepository<Answer, Integer> {
}
