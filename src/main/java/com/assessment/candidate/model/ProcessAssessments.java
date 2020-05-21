package com.assessment.candidate.model;

import lombok.Data;

import java.util.List;

@Data
public class ProcessAssessments {

    private String emailAddress;
    private List<AssessmentStatus> candidateAssessments;

    @Data
    public static class AssessmentStatus {
        private Integer id;
        private boolean status;
    }

}
