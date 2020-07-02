package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemConfiguration {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private String configKey;
    private String configValue;
}
