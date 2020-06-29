package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Builder.Default
    private Integer totalAssessmentScore = 0;
    @Builder.Default
    private Integer totalMarksObtained = 0;

    @Builder.Default
    private ZonedDateTime inviteDate = ZonedDateTime.now();

    @Builder.Default
    private boolean status = false;
    private ZonedDateTime attemptedDate;

    @OneToMany(
            mappedBy = "candidateAssessment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CandidateAssessmentResultSubmission> assessmentResultSubmissions;

}
