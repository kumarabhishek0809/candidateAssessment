package com.assessment.candidate.response;

import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.entity.CandidateAssessment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CandidateSearchResponse extends GenericResponse{

    private List<CandidateAssessment> candidateAssessments;
    private Candidate candidate;


}
