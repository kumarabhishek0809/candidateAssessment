package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvaluationQuestionAnswer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Assessment assessment;

    @OneToOne
    private Question question;

    @OneToOne
    private Options options;

    private Integer marks;



}
