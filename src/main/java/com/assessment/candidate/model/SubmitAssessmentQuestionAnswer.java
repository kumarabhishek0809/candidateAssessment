package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmitAssessmentQuestionAnswer {

    private Integer assessmentId;
    private List<QuestionAnswerReq> questionAnswerReq;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionAnswerReq {
        private Integer questionId;
        private Integer optionId;
    }



}
