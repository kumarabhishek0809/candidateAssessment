package com.assessment.candidate.repository;

import com.assessment.candidate.entity.CandidateAssessment;
import com.assessment.candidate.model.AssessmentCandidateCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface ICandidateAssessmentRepository extends CrudRepository<CandidateAssessment, Integer> {

    @Query("Select a.name as assessmentName , count(ca) as candidateCount from CandidateAssessment as ca, Assessment as a where ca.assessment.id = a.id group by a.name ")
    List<AssessmentCandidateCount> getCandidateAssessmentCount();

    Iterable<CandidateAssessment> findAllByAttemptedDateBefore(ZonedDateTime past180Days);
}
