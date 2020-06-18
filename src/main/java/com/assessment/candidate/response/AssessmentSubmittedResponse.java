package com.assessment.candidate.response;

import com.assessment.candidate.model.Candidate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class AssessmentSubmittedResponse extends GenericResponse{

    private Candidate candidate;
    private boolean dataSubmited;
}
