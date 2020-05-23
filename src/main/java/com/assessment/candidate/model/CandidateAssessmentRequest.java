package com.assessment.candidate.model;

import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.entity.CandidateAssessment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateAssessmentRequest {

    private Candidate candidate;
    private CandidateAssessment candidateAssessment;
}
