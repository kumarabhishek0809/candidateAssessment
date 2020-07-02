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
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String header;

    @OneToOne
    private Answer answer;

    @OneToMany(mappedBy="question",cascade = CascadeType.ALL)
    private List<Options> options ;

    private String technology;

    @OneToOne
    private QuestionType questionType;

}
