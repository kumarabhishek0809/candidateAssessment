package com.assessment.candidate.controller;

import com.assessment.candidate.response.CandidateSearchResponse;
import com.assessment.candidate.service.CandidateSearchService;
import com.assessment.candidate.service.EncodeDecodeService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {

    @Autowired
    private CandidateSearchService candidateSearchService;


    @GetMapping(value = "/candidateDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateSearchResponse getCandidate(@RequestParam("emailId") String emailId){
        CandidateSearchResponse candidateSearchResponse = null;
        if(StringUtils.isNotBlank(emailId)) {
            candidateSearchResponse = candidateSearchService.findCandidateDetailsByEmail(emailId);
        }
        return candidateSearchResponse;
    }
}
