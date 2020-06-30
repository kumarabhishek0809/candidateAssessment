package com.assessment.candidate.response;

import com.assessment.candidate.entity.Assessment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AssessmentQuestionResponse extends GenericResponse{

    private Assessment assessment;

}
