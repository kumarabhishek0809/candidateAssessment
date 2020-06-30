package com.assessment.candidate.model;

import lombok.Data;

@Data
public class EvaluationRequest {

    private Integer assessmentId;
    private Integer questionId;
    private Integer optionId;
    private Integer marks;
}
