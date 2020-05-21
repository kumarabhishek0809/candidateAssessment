package com.assessment.candidate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    private String duration;
    private String name;
    private String technology;

    @OneToMany
    private Set<Question> questions;
}
