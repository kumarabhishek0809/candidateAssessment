package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String header;
    private String description;

    @OneToMany
    private Set<AnswerOption> answerOptions;
}
