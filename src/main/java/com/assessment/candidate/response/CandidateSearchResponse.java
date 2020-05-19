package com.assessment.candidate.response;

import com.assessment.candidate.model.Assessment;
import com.assessment.candidate.model.Candidate;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CandidateSearchResponse {

    private List<Assessment> assessments;
    private Candidate candidate;


}
