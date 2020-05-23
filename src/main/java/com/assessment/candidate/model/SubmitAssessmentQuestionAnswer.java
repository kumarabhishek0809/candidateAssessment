package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmitAssessmentQuestionAnswer {

    private Integer assessmentId;
    private List<QuestionAnswer> questionAnswers;


    @Data
    public static class QuestionAnswer {
        private Integer questionId;
        private Integer answerId;
    }



}
