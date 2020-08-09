package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer documentId;

    private String emailId;
    private String candidateName;
    private String documentName;
    private String image;
    @Builder.Default
    private ZonedDateTime createdDate = ZonedDateTime.now();;
    @Builder.Default
    private ZonedDateTime updatedDate =  ZonedDateTime.now();;
}
