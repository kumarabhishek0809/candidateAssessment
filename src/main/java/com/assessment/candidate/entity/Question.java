package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String header;
    private String description;

    @OneToOne
    private Answer answer;

}
