package com.assessment.candidate.model;

import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.entity.CandidateAssessment;
import lombok.Data;

@Data
public class CandidateAssessmentRequest {

    private Candidate candidate;
    private CandidateAssessment candidateAssessment;
}
