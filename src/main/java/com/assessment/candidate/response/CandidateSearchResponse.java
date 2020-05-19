package com.assessment.candidate.response;

import com.assessment.candidate.model.CandidateAssessment;
import com.assessment.candidate.model.Candidate;
import lombok.*;

import java.util.List;

@Data
@Builder
public class CandidateSearchResponse extends GenericResponse{

    private List<CandidateAssessment> candidateAssessments;
    private Candidate candidate;


}
