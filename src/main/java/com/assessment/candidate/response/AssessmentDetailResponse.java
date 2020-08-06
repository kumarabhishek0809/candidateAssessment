package com.assessment.candidate.response;

import com.assessment.candidate.entity.Answer;
import com.assessment.candidate.entity.Options;
import com.assessment.candidate.entity.QuestionType;
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
        private Integer noOfQuestions;
        private List<Question> questions;
    }


    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Question {
        private Integer id;
        private String header;
        private Answer answer;
        private List<Options> options ;
        private String technology;
        private QuestionType questionType;
        @Builder.Default
        private boolean valid = true;
        private String questionImgPath;

    }

}
