package com.assessment.candidate.response;

import com.assessment.candidate.entity.Assessment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AssessmentDetailResponse extends GenericResponse{

    private List<Assessment> assessments;
}
