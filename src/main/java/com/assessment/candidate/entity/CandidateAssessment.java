package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
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

    private float totalAssessmentScore = 0;
    private float totalMarksObtained = 0;

    @Builder.Default
    private ZonedDateTime inviteDate = ZonedDateTime.now();

    private boolean status = false;
    private ZonedDateTime attemptedDate;
}
