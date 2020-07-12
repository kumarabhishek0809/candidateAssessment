package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsRequest implements Serializable {
    private Integer answerId;
    private Integer questionTypeId;

    private List<Options> options;

    private String header;
    private Integer questionId;
    private String technology;

    private List<Integer> assessmentIds;
    private Integer marks;

    @Builder.Default
    private boolean valid = true;

    @Data
    @Builder
    @EqualsAndHashCode(callSuper = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Options {
        private String description;
        private boolean answerOption;
    }


}

