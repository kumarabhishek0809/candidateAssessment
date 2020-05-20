package com.assessment.candidate.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Candidate {

    private Integer id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String loginId;
    private String countryCode;
    private String dateOfBirth;
    private String mobileNo;

    private List<CandidateAssessment> candidateAssessments;
}
