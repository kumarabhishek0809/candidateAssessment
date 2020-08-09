package com.assessment.candidate.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Integer document_id;

    private String emailId;
    private String candidateName;
    private String documentName;

    @Column(length = 500000)
    private String image;

    @Builder.Default
    private ZonedDateTime createdDate = ZonedDateTime.now();;
    @Builder.Default
    private ZonedDateTime updatedDate =  ZonedDateTime.now();;
}
