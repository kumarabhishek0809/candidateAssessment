package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateAssessment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    //post process
    private String result;
    private String percentage;
    private String action;

    private boolean active = true;

    //While Registering
    @OneToOne
    private Assessment assessment;

    @ManyToOne
    private Candidate candidate;

    float totalAssessmentScore = 0;
    float totalMarksObtained = 0;
}
