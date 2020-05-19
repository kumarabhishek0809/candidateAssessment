package com.assessment.candidate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Candidate {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String loginId;
    private String countryCode;
    private String dateOfBirth;
    private String mobileNo;
}
