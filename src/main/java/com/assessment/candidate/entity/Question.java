package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String header;

    @OneToOne
    private Answer answer;

    @OneToMany
    private Set<Options> options ;
    private String technology;
    @OneToOne
    private QuestionType questionType;




}
