package com.assessment.candidate.controller;

import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.model.CandidateAssessmentRequest;
import com.assessment.candidate.response.CandidateSearchResponse;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.service.CandidateService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;



    @GetMapping(value = "/candidateDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateSearchResponse getCandidate(@RequestParam("emailId") String emailId){
        CandidateSearchResponse candidateSearchResponse = null;
        if(StringUtils.isNotBlank(emailId)) {
            candidateSearchResponse = candidateService.findCandidateDetailsByEmail(emailId);
        }
        return candidateSearchResponse;
    }

    @PostMapping(value = "/registerCandidate" )
    public GenericResponse registerCandidate(@RequestBody Candidate candidate){
        return candidateService.registerCandidate(candidate);
    }

    @PostMapping(value = "/registerCandidateScheduleAssessment" )
    public GenericResponse registerCandidateAndScheduleAssessment(@RequestBody CandidateAssessmentRequest candidate){
        return candidateService.registerCandidateAndScheduleAssessment(candidate);
    }
}
