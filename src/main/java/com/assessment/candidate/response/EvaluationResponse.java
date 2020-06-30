package com.assessment.candidate.response;

import com.assessment.candidate.entity.Options;
import lombok.Data;

@Data
public class EvaluationResponse extends GenericResponse{

    private Integer id;
    private String question;
    private Options options;
    private Integer marks;

}
