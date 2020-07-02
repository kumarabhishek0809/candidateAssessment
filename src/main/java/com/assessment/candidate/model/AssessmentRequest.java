package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentRequest {
    private Integer assessmentId;
    private String duration;
    private Integer passingPercentage;
    private Integer questionCount;
    private String name;
    private String technology;
    private List<Integer> questionIds;

}
