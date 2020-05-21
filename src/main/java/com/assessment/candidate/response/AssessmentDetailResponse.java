package com.assessment.candidate.response;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.model.Candidate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssessmentDetailResponse extends GenericResponse{

    private Candidate candidate;
    private Assessment assessments;
}
