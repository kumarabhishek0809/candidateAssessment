package com.assessment.candidate.controller;

import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.model.CandidateAssessmentRequest;
import com.assessment.candidate.model.ProcessAssessments;
import com.assessment.candidate.response.CandidateSearchResponse;
import com.assessment.candidate.response.CandidatesSearchResponse;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.service.CandidateService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.net.UnknownHostException;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;



    @GetMapping(value = "/candidateDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateSearchResponse getCandidate(@RequestParam("emailId") String emailId){
        CandidateSearchResponse candidateSearchResponse = null;
        if(StringUtils.isNotBlank(emailId)) {
            candidateSearchResponse = candidateService.findCandidateDetailsByEmail(emailId);
        }
        return candidateSearchResponse;
    }

    @GetMapping(value = "/candidateDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidatesSearchResponse getCandidateDetails(){
        CandidatesSearchResponse candidateSearchResponse = null;
        candidateSearchResponse = candidateService.findCandidateDetails();
        return candidateSearchResponse;
    }

    @PutMapping(value = "/registerCandidate" )
    public GenericResponse registerCandidate(@RequestBody Candidate candidate){
        return candidateService.registerCandidate(candidate);
    }

    @PutMapping(value = "/processAssessmentForCandidate")
    public GenericResponse processAssessmentForCandidate(@RequestBody ProcessAssessments candidateAssessments){
        return candidateService.processAssessmentForCandidate(candidateAssessments);
    }

    @PostMapping(value = "/registerCandidateScheduleAssessment" )
    public GenericResponse registerCandidateAndScheduleAssessment(@RequestBody CandidateAssessmentRequest candidate) throws UnknownHostException, MessagingException {
        if(candidate == null || candidate.getCandidate() == null || candidate.getCandidate().getEmailAddress() == null){
            throw new RuntimeException();
        }
        return candidateService.registerCandidateAndScheduleAssessment(candidate);
    }
}
