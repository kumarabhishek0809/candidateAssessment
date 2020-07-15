package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmitAssessmentReq {
    private Integer assessmentId;
    private Map<Integer,Integer> questionAnswerReq;
}
