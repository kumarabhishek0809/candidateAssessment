package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessAssessments {

    private String emailAddress;
    private List<AssessmentStatus> candidateAssessments;

    @Data
    public static class AssessmentStatus {
        private Integer id;
        private boolean status;
    }

}
