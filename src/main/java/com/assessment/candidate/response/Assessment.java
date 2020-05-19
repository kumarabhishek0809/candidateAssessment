package com.assessment.candidate.response;

import com.assessment.candidate.response.GenericResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Assessment extends GenericResponse {
    private String duration;
    private String name;
    private String id;
    private String technology;
}
