package com.assessment.candidate.repository;

import com.assessment.candidate.entity.Assessment;
import org.springframework.data.repository.CrudRepository;

public interface IAssessmentRepository extends CrudRepository<Assessment, Integer> {
}
