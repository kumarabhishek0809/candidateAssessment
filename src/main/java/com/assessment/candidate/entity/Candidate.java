package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String countryCode;
    private String dateOfBirth;
    private String mobileNo;

    @OneToMany(
            mappedBy = "candidate",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CandidateAssessment> candidateAssessments;
}
