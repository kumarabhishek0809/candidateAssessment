package com.assessment.candidate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Assessment {

    private Integer id;
    private String duration;
    private String name;
    private String technology;

}
