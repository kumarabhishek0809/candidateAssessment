package com.assessment.candidate.service;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.entity.CandidateAssessment;
import com.assessment.candidate.model.CandidateAssessmentRequest;
import com.assessment.candidate.model.Email;
import com.assessment.candidate.model.ProcessAssessments;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.repository.ICandidateAssessmentRepository;
import com.assessment.candidate.repository.ICandidateRepository;
import com.assessment.candidate.response.CandidateSearchResponse;
import com.assessment.candidate.response.CandidatesSearchResponse;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private ICandidateAssessmentRepository candidateAssessmentRepository;
    @Autowired
    private IAssessmentRepository assessmentRepository;
    @Autowired
    private AssessmentsService assessmentsService;
    @Autowired
    private EmailService emailService;

    @Value("${instanceIPAddress}")
    private String instanceIPAddress;


    public CandidatesSearchResponse findCandidateDetails() {
        CandidatesSearchResponse candidateSearchResponse = CandidatesSearchResponse.builder().build();
        candidateSearchResponse.setCandidates(new ArrayList<>());
        List<CandidatesSearchResponse.CandidateProfile> candidateProfiles = candidateSearchResponse.getCandidates();
        Iterable<Candidate> candidateRepositoryAll = candidateRepository.findAll();
        candidateSearchResponse.setDataAvailable(true);

        candidateRepositoryAll.forEach(candidateEntity ->
        {
            List<CandidateAssessment> candidateAssessments = candidateEntity.getCandidateAssessments();
            candidateProfiles.addAll(candidateAssessments.stream().map(candidateAssessment -> {
                return CandidatesSearchResponse.CandidateProfile.builder()
                        .name(candidateAssessment.getAssessment().getName())
                        .id(candidateAssessment.getId())
                        .firstName(candidateEntity.getFirstName())
                        .lastName(candidateEntity.getLastName())
                        .emailAddress(candidateEntity.getEmailAddress())
                        .countryCode(candidateEntity.getCountryCode())
                        .dateOfBirth(candidateEntity.getDateOfBirth())
                        .mobileNo(candidateEntity.getMobileNo())
                        .inviteDate(DateUtils.getStringDate(candidateAssessment.getInviteDate()))
                        .attemptedDate(DateUtils.getStringDate(candidateAssessment.getAttemptedDate()))
                        .action(candidateAssessment.getAction())
                        .percentage(candidateAssessment.getPercentage())
                        .result(candidateAssessment.getResult())
                        .attempted(candidateAssessment.isAttempted())
                        .passFail(candidateAssessment.isPassFail())
                        .build();
            }).collect(Collectors.toList()));
        });
        return candidateSearchResponse;
    }

    private Function<CandidateAssessment, com.assessment.candidate.model.CandidateAssessment> getCandidateAssessmentCandidateAssessmentFunction() {
        return candidateAssessment ->
                com.assessment.candidate.model.CandidateAssessment.builder()
                        .assessment(assessmentsService.mapEntityToModel(candidateAssessment.getAssessment()))
                        .action(candidateAssessment.getAction())
                        .id(candidateAssessment.getId())
                        .percentage(candidateAssessment.getPercentage())
                        .result(candidateAssessment.getResult())
                        .attempted(candidateAssessment.isAttempted())
                        .inviteDate(DateUtils.getStringDate(candidateAssessment.getInviteDate()))
                        .attemptedDate(DateUtils.getStringDate(candidateAssessment.getAttemptedDate()))
                        .passFail(candidateAssessment.isPassFail())
                        .build();
    }

    public CandidateSearchResponse findCandidateDetailsByEmail(String emailId) {
        CandidateSearchResponse candidateSearchResponse = CandidateSearchResponse.builder().build();
        if (!StringUtils.isEmpty(emailId)) {
            Optional<com.assessment.candidate.entity.Candidate> byEmailAddress = candidateRepository.findByEmailAddress(emailId);
            if (byEmailAddress.isPresent()) {
                com.assessment.candidate.entity.Candidate candidate = byEmailAddress.get();

                List<CandidateAssessment> candidateAssessments = candidate.getCandidateAssessments();
                List<com.assessment.candidate.model.CandidateAssessment> assessments = candidateAssessments.stream()
                        .filter(candidateAssessment -> !candidateAssessment.isAttempted())
                        .map(getCandidateAssessmentCandidateAssessmentFunction()
                        ).collect(Collectors.toList());

                candidateSearchResponse = CandidateSearchResponse.builder()
                        .candidate(mapEntityToModel(candidate, assessments))
                        .build();
                candidateSearchResponse.setDataAvailable(true);

            }
        }
        return candidateSearchResponse;
    }

    public com.assessment.candidate.model.Candidate mapEntityToModel(Candidate candidate,
                                                                     List<com.assessment.candidate.model.CandidateAssessment> assessments) {

        String inviteDate = "";
        String attemptedDate = "";

        if (!CollectionUtils.isEmpty(assessments)) {
            com.assessment.candidate.model.CandidateAssessment candidateAssessment = assessments.get(0);
            inviteDate = candidateAssessment.getInviteDate();
            attemptedDate = candidateAssessment.getAttemptedDate();
        }

        return com.assessment.candidate.model.Candidate.builder()
                .mobileNo(candidate.getMobileNo())
                .countryCode(candidate.getCountryCode())
                .dateOfBirth(candidate.getDateOfBirth())
                .emailAddress(candidate.getEmailAddress())
                .firstName(candidate.getFirstName())
                .id(candidate.getId())
                .lastName(candidate.getLastName())
                .candidateAssessments(assessments)
                .build();
    }

    public GenericResponse registerCandidate(Candidate candidate) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);
        //candidate.getCandidateAssessments().forEach(  candidateAssessment -> candidateAssessment.setInviteDate(ZonedDateTime.now()));
        com.assessment.candidate.entity.Candidate candidateEntity = candidateRepository.save(candidate);
        System.out.println(candidateEntity.getId());
        return genericResponse;
    }

    public GenericResponse registerCandidateAndScheduleAssessment(CandidateAssessmentRequest candidateAssessmentRequest) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);
        String assessmentName = "Technical";
        //FindCandidate if not then save
        Candidate candidateEntity = null;
        Assessment assessment = null;
        Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(candidateAssessmentRequest.getCandidate().getEmailAddress());
        if (byEmailAddress.isPresent()) {
            candidateEntity = byEmailAddress.get();
            Candidate candidateRequest = candidateAssessmentRequest.getCandidate();
            candidateEntity.setMobileNo(Optional.ofNullable(candidateEntity.getMobileNo()).orElse(candidateRequest.getMobileNo()));
            candidateEntity.setLastName(Optional.ofNullable(candidateEntity.getLastName()).orElse(candidateRequest.getLastName()));
            candidateEntity.setFirstName(Optional.ofNullable(candidateEntity.getFirstName()).orElse(candidateRequest.getFirstName()));
            candidateEntity.setEmailAddress(Optional.ofNullable(candidateEntity.getEmailAddress()).orElse(candidateRequest.getEmailAddress()));
            candidateEntity.setDateOfBirth(Optional.ofNullable(candidateEntity.getDateOfBirth()).orElse(candidateRequest.getDateOfBirth()));
            candidateEntity.setCountryCode(Optional.ofNullable(candidateEntity.getCountryCode()).orElse(candidateRequest.getCountryCode()));
        } else {
            candidateEntity = candidateRepository.save(candidateAssessmentRequest.getCandidate());
        }
        System.out.println(candidateEntity.getId());

        CandidateAssessment candidateAssessment = candidateAssessmentRequest.getCandidateAssessment();
        if (candidateAssessment != null && candidateAssessment.getAssessment() != null && candidateAssessment.getAssessment().getId() != null) {
            Optional<Assessment> byId = assessmentRepository.findById(candidateAssessment.getAssessment().getId());
            if (byId.isPresent()) {
                assessment = byId.get();
                assessmentName = assessment.getName();
                CandidateAssessment newAssessment = candidateAssessment;
                newAssessment.setCandidate(candidateEntity);
                newAssessment.setAssessment(assessment);
                CandidateAssessment canAssessment = candidateAssessmentRepository.save(newAssessment);
                System.out.println(assessment.getId());
            }

            //Send Email,
            if (candidateEntity != null && assessment != null) {
                Email email = Email.builder().subject("Synechron invites you to take " + assessmentName + " Assessment")
                        .message("Dear " + candidateEntity.getFirstName() + ",\n" +
                                "\n" +
                                "You have been invited to take the assessment " + assessmentName + " Assessment. " +
                                "The duration of this test is " + assessment.getDuration() + " mins. Before you proceed to take the assessment " +
                                "Please click on the link given below to start the test.\n" +
                                "\n" +
                                "http://" + instanceIPAddress + ":8080/assessment/" + assessment.getId() + "?emailId=" + candidateEntity.getEmailAddress() + "\n" +
                                ",\n" +
                                "All the best!\n" +
                                "\n" +
                                "Regards ,\n" +
                                "Synechron ")
                        .toEmail(candidateEntity.getEmailAddress())
                        .build();
                emailService.sendMail(email);
            }
        }
        return genericResponse;
    }

    public GenericResponse processAssessmentForCandidate(ProcessAssessments candidateAssessments) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);

        Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(candidateAssessments.getEmailAddress());
        if (byEmailAddress.isPresent()) {
            Candidate candidateDb = byEmailAddress.get();
            List<CandidateAssessment> dbCandidateAssessments = candidateDb.getCandidateAssessments();
            List<ProcessAssessments.AssessmentStatus> assessmentStatusRequest = candidateAssessments.getCandidateAssessments();

            if (!CollectionUtils.isEmpty(dbCandidateAssessments)) {
                for (ProcessAssessments.AssessmentStatus assessmentStatus : assessmentStatusRequest) {

                    for (CandidateAssessment candidateAssessment : dbCandidateAssessments) {
                        if (candidateAssessment.getId() == assessmentStatus.getId()
                                && candidateAssessment.isAttempted() != assessmentStatus.isStatus()) {
                            candidateAssessment.setAttempted(assessmentStatus.isStatus());
                        }
                    }
                }
            }
            candidateAssessmentRepository.saveAll(dbCandidateAssessments);
        }

        return genericResponse;
    }
}
