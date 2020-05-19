package com.assessment.candidate.service;

import com.assessment.candidate.model.CandidateAssessment;
import com.assessment.candidate.model.Candidate;
import com.assessment.candidate.response.CandidateSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateSearchService {
    public CandidateSearchResponse findCandidateDetailsByEmail(String emailId) {
        CandidateSearchResponse candidateSearchResponse = CandidateSearchResponse.builder().build();
        if(!StringUtils.isEmpty(emailId) && "john.doe@gmail.com".equals(emailId)) {
            candidateSearchResponse = CandidateSearchResponse.builder()
                    .candidateAssessments(getAssessments())
                    .candidate(Candidate.builder()
                            .countryCode("971")
                            .dateOfBirth("20-07-1982")
                            .emailAddress("john.doe@gmail.com")
                            .firstName("John")
                            .lastName("Doe")
                            .loginId("john.doe@gmail.com")
                            .mobileNo("8975425323232")
                            .build())
                    .build();
            candidateSearchResponse.setDataAvailable(true);
        }
        return candidateSearchResponse;
    }

    private List<CandidateAssessment> getAssessments() {
        List<CandidateAssessment> candidateAssessments = new ArrayList<CandidateAssessment>();
        CandidateAssessment candidateAssessmentJava = CandidateAssessment.builder().name("JAVA101").percentage("70").result("PASS").action("Processed").build();
        CandidateAssessment candidateAssessmentPhyton = CandidateAssessment.builder().name("Phyton101").percentage("40").result("FAIL").action("Not-Processed").build();
        CandidateAssessment candidateAssessmentReact = CandidateAssessment.builder().name("REACT101").percentage("0").result("Not-Attempt").action("").build();
        candidateAssessments.add(candidateAssessmentJava);
        candidateAssessments.add(candidateAssessmentPhyton);
        candidateAssessments.add(candidateAssessmentReact);
        return candidateAssessments;
    }
}
