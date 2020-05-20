package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    private String duration;
    private String name;
    private String technology;
}
