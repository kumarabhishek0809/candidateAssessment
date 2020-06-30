package com.assessment.candidate.response;

import com.assessment.candidate.entity.Question;
import lombok.Data;

@Data
public class QuestionAddResponse extends GenericResponse{

    private Question question;

}
