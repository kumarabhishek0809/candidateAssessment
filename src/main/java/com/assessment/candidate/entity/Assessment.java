package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;


    private String duration;
    private String name;
    private String technology;
    private Integer passingPercentage;
    private Integer questionCount;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

}
