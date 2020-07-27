package com.assessment.candidate.response;

import com.assessment.candidate.entity.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QuestionAddResponse extends GenericResponse{

    private Question question;
    private EvaluationResponse evaluationResponse;
    private String message;

}
