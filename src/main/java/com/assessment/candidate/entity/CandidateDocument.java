package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String emailId;
    private String candidateName;
    private String documentId;
    private String documentName;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
}
