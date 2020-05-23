package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerOption {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String description;

    private Integer marks;
}
