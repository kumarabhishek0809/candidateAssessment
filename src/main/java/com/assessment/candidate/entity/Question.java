package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy="question")
    private List<Options> options ;

    private String technology;

    @OneToOne
    private QuestionType questionType;




}
