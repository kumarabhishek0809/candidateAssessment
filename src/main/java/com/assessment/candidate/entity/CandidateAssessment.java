package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CandidateAssessment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    //post process
    private String result;
    private String percentage;
    private String action;

    //While Registering
    @OneToOne
    private Assessment assessment;

    @ManyToOne
    private Candidate candidate;
}
