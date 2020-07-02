package com.assessment.candidate.response;

import com.assessment.candidate.entity.Question;
import com.assessment.candidate.model.Candidate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class AssessmentDetailResponse extends GenericResponse{

    private Candidate candidate;
    private Assessment assessments;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Assessment {
        private Integer id;
        private String duration;
        private String name;
        private String technology;
        private Integer passingPercentage;

        private List<Question> questions;
    }

}
