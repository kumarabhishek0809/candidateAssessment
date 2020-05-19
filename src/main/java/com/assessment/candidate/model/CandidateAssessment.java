package com.assessment.candidate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateAssessment {

    private String result;
    private String percentage;
    private String name;
    private String action;
}
