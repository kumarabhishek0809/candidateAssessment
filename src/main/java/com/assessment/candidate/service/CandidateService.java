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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.assessment.candidate.CandidateApplication.CANDIDATE_CACHE;

@Service
public class CandidateService {

    @Autowired
    private ICandidateRepository candidateRepository;
    @Autowired
    private ICandidateAssessmentRepository candidateAssessmentRepository;
    @Autowired
    private IAssessmentRepository assessmentRepository;
    @Autowired
    private AssessmentCandidateMapper assessmentCandidateMapper;
    @Autowired
    private EmailService emailService;

    @Value("${instanceIPAddress}")
    private String instanceIPAddress;


    @Cacheable(value = CANDIDATE_CACHE, key = " 'findCandidateDetails' ")
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
                        .status(candidateAssessment.isStatus())
                        .build();
            }).collect(Collectors.toList()));
        });
        return candidateSearchResponse;
    }

    private Function<CandidateAssessment, com.assessment.candidate.model.CandidateAssessment> getCandidateAssessmentCandidateAssessmentFunction() {
        return candidateAssessment ->
                com.assessment.candidate.model.CandidateAssessment.builder()
                        .assessment(assessmentCandidateMapper.mapEntityToModel(candidateAssessment.getAssessment()))
                        .action(candidateAssessment.getAction())
                        .id(candidateAssessment.getId())
                        .percentage(candidateAssessment.getPercentage())
                        .result(candidateAssessment.getResult())
                        .attempted(candidateAssessment.isStatus())
                        .inviteDate(DateUtils.getStringDate(candidateAssessment.getInviteDate()))
                        .attemptedDate(DateUtils.getStringDate(candidateAssessment.getAttemptedDate()))
                        .status(candidateAssessment.isStatus())
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
                        .filter(candidateAssessment -> !candidateAssessment.isStatus())
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

        Candidate candidateEntity = updateCandidateDetails(candidate);
        candidateEntity = candidateRepository.save(candidateEntity);


        System.out.println(candidateEntity.getId());
        return genericResponse;
    }

    public GenericResponse registerCandidateAndScheduleAssessment(CandidateAssessmentRequest candidateAssessmentRequest) throws MessagingException {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);
        String assessmentName = "Technical";

        //FindCandidate if not then save
        Candidate candidateEntity = null;
        Assessment assessment = null;
        candidateEntity = updateCandidateDetails(candidateAssessmentRequest.getCandidate());
        System.out.println(candidateEntity.getId());

        CandidateAssessment candidateAssessment = candidateAssessmentRequest.getCandidateAssessment();
        if (candidateAssessment != null && candidateAssessment.getAssessment() != null && candidateAssessment.getAssessment().getId() != null) {
            assessment = assessmentRepository.findById(candidateAssessment.getAssessment().getId())
                    .orElseThrow(() -> new RuntimeException("Assessment ID incorrect " + candidateAssessment.getAssessment().getId()));

            assessmentName = assessment.getName();
            CandidateAssessment newAssessment = candidateAssessment;
            newAssessment.setCandidate(candidateEntity);
            newAssessment.setAssessment(assessment);
            CandidateAssessment canAssessment = candidateAssessmentRepository.save(newAssessment);
            System.out.println(assessment.getId());

            //Send Email,
            if (candidateEntity != null && assessment != null) {
                //?myparam1={id1}&myparam2={id2}
                //?emailId=KUMAR.ABHISHEK1@synechron.com&assessmentId=3
                String testLink = "http://" + instanceIPAddress +
                        ":3000/CandidateRegisterForAssessment?emailId="
                        + candidateEntity.getEmailAddress()
                        + "&assessmentId=" + assessment.getId()
                        + "&assessmentName=" + assessment.getName();
                Email email = Email.builder().subject("Synechron invites you to take " + assessmentName + " Assessment")
                        .message(

                                "<i> Dear  <b>" + candidateEntity.getFirstName() + " </b> Greetings!</i><br>" +
                                        "<b> Wish you a nice day! </b> <br> <br>" +
                                        "<h4>" +
                                        "Please to inform, your profile is shortlisted for Next Level…. Congratulation !!!!" + "<br><br>" +
                                        "As a of recruitment process, request you to please complete the Assessment : <b>" + assessmentName + " <b> <br>" +
                                        "Test Link:  <a href=" + testLink + "> Assessment Link </a>  <br> <br> <br> <br>" +
                                        "Instructions to follow :" + "<br> <br>" +
                                        "Once you get the test link please login with your credentials.  <br>  " +
                                        "It’s a " + assessment.getDuration() + "  Min Online Test.<br>" +

                                        "To ensure an uninterrupted test taking experience , you may close all chat windows, Screen savers, multiple windows etc. before starting the test. <br> " +
                                        "Please do not press “F5” during the test. This will finish your test and you will not be able to re-open the test.   <br>" +
                                        "Once you complete the test, please revert us to get your result. <br><br><br>" +

                                        "<b><font color=red>Note : Please DO NOT try to copy anything as an automated flag will be raised against your name and you will get dis-qualified. </font> </b> <br>" +


                                        "</h4>" +
                                        "Regards , <br>" +
                                        "Synechron ")
                        .toEmail(candidateEntity.getEmailAddress())
                        .build();
                emailService.sendMail(email);
            }
        }
        return genericResponse;
    }

    public Candidate updateCandidateDetails(Candidate candidateRequest) {
        Candidate candidateEntity;
        Optional<Candidate> byEmailAddress = candidateRepository.findByEmailAddress(candidateRequest.getEmailAddress());
        if (byEmailAddress.isPresent()) {
            candidateEntity = byEmailAddress.get();
            candidateEntity.setMobileNo(getFirstNotNullString(candidateRequest.getMobileNo(), candidateEntity.getMobileNo()));
            candidateEntity.setLastName(getFirstNotNullString(candidateRequest.getLastName(), candidateEntity.getLastName()));
            candidateEntity.setFirstName(getFirstNotNullString(candidateRequest.getFirstName(), candidateEntity.getFirstName()));
            candidateEntity.setEmailAddress(getFirstNotNullString(candidateRequest.getEmailAddress(), candidateEntity.getEmailAddress()));
            candidateEntity.setDateOfBirth(getFirstNotNullString(candidateRequest.getDateOfBirth(), candidateEntity.getDateOfBirth()));
            candidateEntity.setCountryCode(getFirstNotNullString(candidateRequest.getCountryCode(), candidateEntity.getCountryCode()));
        } else {
            candidateEntity = candidateRepository.save(candidateRequest);
        }
        return candidateEntity;
    }

    public String getFirstNotNullString(String requestString, String databaseString) {
        String notNullString = Optional.ofNullable(requestString).filter(str -> !StringUtils.isEmpty(str.trim()))
                .orElse(databaseString);
        return notNullString;
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

                    dbCandidateAssessments.stream().filter(candidateAssessment -> (candidateAssessment.getId() == assessmentStatus.getId()
                            && candidateAssessment.isStatus() != assessmentStatus.isStatus())).forEach(candidateAssessment ->
                    {
                        candidateAssessment.setAttemptedDate(ZonedDateTime.now());
                        candidateAssessment.setPercentage(getFirstNotNullString(candidateAssessment.getPercentage(), "0"));
                        candidateAssessment.setAction(getFirstNotNullString(candidateAssessment.getAction(), assessmentStatus.isStatus() ? "Completed By HR" : "Reassigned By HR"));
                        candidateAssessment.setStatus(assessmentStatus.isStatus());
                    });
                }
            }
            candidateAssessmentRepository.saveAll(dbCandidateAssessments);
        }

        return genericResponse;
    }
}
