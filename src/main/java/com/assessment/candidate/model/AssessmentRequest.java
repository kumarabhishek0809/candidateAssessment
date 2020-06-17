package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssessmentRequest {
    private String duration;
    private Integer passingPercentage;
    private String name;
    private String technology;
    private List<Integer> questionIds;
}
