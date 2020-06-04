package com.assessment.candidate.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CandidatesSearchResponse extends GenericResponse {

    private List<CandidateProfile> candidates;

    @Data
    @Builder
    @EqualsAndHashCode(callSuper = false)
    public static class CandidateProfile {

        private Integer id;

        private String firstName;
        private String lastName;
        private String emailAddress;
        private String countryCode;
        private String dateOfBirth;
        private String mobileNo;
        private String inviteDate;
        private String attemptedDate;
        private String result;
        private String percentage;
        private String action;
        private boolean attempted;
        private boolean passFail;
        private String name;
    }

}
