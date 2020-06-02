package com.assessment.candidate.response;

import com.assessment.candidate.model.Assessment;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class AssessmentResponse extends GenericResponse{

    private List<Assessment> assessments;
}
