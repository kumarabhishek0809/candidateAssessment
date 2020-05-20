package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CandidateAssessment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String result;
    private String percentage;
    private String name;
    private String action;
    @ManyToOne
    private Candidate candidate;
}
