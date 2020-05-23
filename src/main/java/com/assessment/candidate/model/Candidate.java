package com.assessment.candidate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Candidate {

    private Integer id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String countryCode;
    private String dateOfBirth;
    private String mobileNo;

    private List<CandidateAssessment> candidateAssessments;
}
