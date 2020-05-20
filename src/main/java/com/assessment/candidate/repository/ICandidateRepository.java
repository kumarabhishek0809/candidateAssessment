package com.assessment.candidate.repository;

import com.assessment.candidate.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICandidateRepository extends CrudRepository<Candidate, Integer> {
    Optional<Candidate> findByEmailAddress(String emailId);
}
