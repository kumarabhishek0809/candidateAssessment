package com.assessment.candidate.model;

import com.assessment.candidate.entity.Assessment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CandidateAssessment {

    private Integer id;

    //post process
    private String result;
    private String percentage;
    private String action;
    private boolean active;
    private Assessment assessment;
}
