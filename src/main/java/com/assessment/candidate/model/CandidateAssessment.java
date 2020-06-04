package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateAssessment {

    private Integer id;

    //post process
    private String result;
    private String percentage;
    private String action;
    private boolean attempted;
    private boolean passFail;
    private Assessment assessment;

    private String inviteDate;
    private String attemptedDate;
}
