package com.assessment.candidate.service;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.entity.CandidateAssessment;
import com.assessment.candidate.model.CandidateAssessmentRequest;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.ICandidateAssessmentRepository;
import com.assessment.candidate.repository.ICandidateRepository;
import com.assessment.candidate.response.CandidateSearchResponse;
import com.assessment.candidate.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private ICandidateAssessmentRepository candidateAssessmentRepository;
    @Autowired
    private IAssessmentRepository assessmentRepository;

    public CandidateSearchResponse findCandidateDetailsByEmail(String emailId) {
        CandidateSearchResponse candidateSearchResponse = CandidateSearchResponse.builder().build();
        if (!StringUtils.isEmpty(emailId)) {
            Optional<com.assessment.candidate.entity.Candidate> byEmailAddress = candidateRepository.findByEmailAddress(emailId);
            if (byEmailAddress.isPresent()) {
                com.assessment.candidate.entity.Candidate candidate = byEmailAddress.get();

                List<CandidateAssessment> candidateAssessments = candidate.getCandidateAssessments();
                List<com.assessment.candidate.model.CandidateAssessment> assessments = candidateAssessments.stream()
                        .map(candidateAssessment -> com.assessment.candidate.model.CandidateAssessment.builder()
                                .assessment(candidateAssessment.getAssessment())
                                .action(candidateAssessment.getAction())
                                .id(candidateAssessment.getId())
                                .percentage(candidateAssessment.getPercentage())
                                .result(candidateAssessment.getResult())
                                .build()
                        ).collect(Collectors.toList());

                candidateSearchResponse = CandidateSearchResponse.builder()
                        .candidate(com.assessment.candidate.model.Candidate.builder()
                                .mobileNo(candidate.getMobileNo())
                                .countryCode(candidate.getCountryCode())
                                .dateOfBirth(candidate.getDateOfBirth())
                                .emailAddress(candidate.getEmailAddress())
                                .firstName(candidate.getFirstName())
                                .id(candidate.getId())
                                .lastName(candidate.getLastName())
                                .loginId(candidate.getLoginId())
                                .candidateAssessments(assessments)
                                .build())
                        .build();
                candidateSearchResponse.setDataAvailable(true);

            }


        }
        return candidateSearchResponse;
    }

    public GenericResponse registerCandidate(Candidate candidate) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);
        com.assessment.candidate.entity.Candidate candidateEntity = candidateRepository.save(candidate);
        System.out.println(candidateEntity.getId());
        return genericResponse;
    }

    public GenericResponse registerCandidateAndScheduleAssessment(CandidateAssessmentRequest candidate) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);

        //FindCandidate if not then save
        Candidate candidateEntity = null;
        Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(candidate.getCandidate().getEmailAddress());
        if (byEmailAddress.isPresent()) {
            candidateEntity = byEmailAddress.get();
        } else {
            candidateEntity = candidateRepository.save(candidate.getCandidate());
        }
        System.out.println(candidateEntity.getId());

        Optional<Assessment> byId = assessmentRepository.findById(candidate.getCandidateAssessment().getAssessment().getId());
        if (byId.isPresent()) {
            Assessment assessment = byId.get();
            CandidateAssessment candidateAssessment = candidate.getCandidateAssessment();
            candidateAssessment.setCandidate(candidateEntity);
            candidateAssessment.setAssessment(assessment);
            CandidateAssessment canAssessment = candidateAssessmentRepository.save(candidateAssessment);
            System.out.println(assessment.getId());
        }


        return genericResponse;
    }
}
