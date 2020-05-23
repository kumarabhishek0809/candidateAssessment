package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class QuestionAnswerOption {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Question question;

    @OneToOne
    private AnswerOption correctOptionId;



}
