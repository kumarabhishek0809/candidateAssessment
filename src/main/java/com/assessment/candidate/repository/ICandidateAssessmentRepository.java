package com.assessment.candidate.repository;

import com.assessment.candidate.entity.CandidateAssessment;
import org.springframework.data.repository.CrudRepository;

public interface ICandidateAssessmentRepository extends CrudRepository<CandidateAssessment, Integer> {
}
