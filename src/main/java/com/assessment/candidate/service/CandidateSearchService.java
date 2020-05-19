package com.assessment.candidate.service;

import com.assessment.candidate.model.Assessment;
import com.assessment.candidate.model.Candidate;
import com.assessment.candidate.response.CandidateSearchResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateSearchService {
    public CandidateSearchResponse findCandidateDetailsByEmail(String emailId) {
        CandidateSearchResponse candidateSearchResponse = CandidateSearchResponse.builder()
                .assessments(getAssessments()).candidate(Candidate.builder().countryCode("971").dateOfBirth("20-07-1982")
                        .emailAddress("john.doe@gmail.com")
                        .firstName("John")
                        .lastName("Doe")
                        .loginId("john.doe@gmail.com")
                        .mobileNo("8975425323232")
                        .build()).build();
        return candidateSearchResponse;
    }

    private List<Assessment> getAssessments() {
        List<Assessment> assessments = new ArrayList<Assessment>();
        Assessment assessmentJava = Assessment.builder().name("JAVA101").percentage("70").result("PASS").action("Processed").build();
        Assessment assessmentPhyton = Assessment.builder().name("Phyton101").percentage("40").result("FAIL").action("Not-Processed").build();
        Assessment assessmentReact = Assessment.builder().name("REACT101").percentage("0").result("Not-Attempt").action("").build();
        assessments.add(assessmentJava);
        assessments.add(assessmentPhyton);
        assessments.add(assessmentReact);
        return assessments;
    }
}
