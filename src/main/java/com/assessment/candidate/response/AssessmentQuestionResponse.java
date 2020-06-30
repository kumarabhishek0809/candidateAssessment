package com.assessment.candidate.response;

import com.assessment.candidate.entity.Assessment;
import lombok.Data;

@Data
public class AssessmentQuestionResponse extends GenericResponse{

    private Assessment assessment;

}
