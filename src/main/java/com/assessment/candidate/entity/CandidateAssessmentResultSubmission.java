package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//TODO ONCE a new environment is provided we will move this to Mongo or any No-SQL


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateAssessmentResultSubmission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer questionId;
    private Integer optionId;

    @ManyToOne
    private CandidateAssessment candidateAssessment;

}
