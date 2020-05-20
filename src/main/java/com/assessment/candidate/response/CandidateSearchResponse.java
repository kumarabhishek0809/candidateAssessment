package com.assessment.candidate.response;

import com.assessment.candidate.model.Candidate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateSearchResponse extends GenericResponse{

    private Candidate candidate;


}
