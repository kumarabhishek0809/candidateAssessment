package com.assessment.candidate.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidatesSearchResponse extends GenericResponse {

    private List<CandidateProfile> candidates;

    @Data
    @Builder
    @EqualsAndHashCode(callSuper = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
        private boolean status;
        private String name;
    }

}
