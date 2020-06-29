package com.assessment.candidate.controller;

import com.assessment.candidate.entity.Candidate;
import com.assessment.candidate.model.CandidateAssessmentRequest;
import com.assessment.candidate.model.ProcessAssessments;
import com.assessment.candidate.response.CandidateSearchResponse;
import com.assessment.candidate.response.CandidatesSearchResponse;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.service.CandidateAssessmentExcelGenerator;
import com.assessment.candidate.service.CandidateService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

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

    @GetMapping(value = "/download/candidateDetails.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport()
            throws IOException {

        CandidatesSearchResponse candidateSearchResponse = candidateService.findCandidateDetails();
        List<CandidatesSearchResponse.CandidateProfile> candidates = candidateSearchResponse.getCandidates();

        ByteArrayInputStream in = CandidateAssessmentExcelGenerator.customersToExcel(candidates);
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "attachment; filename=candidateDetails.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping(value = "/registerCandidate" )
    public GenericResponse registerCandidate(@RequestBody Candidate candidate){
        if(candidate == null || candidate.getEmailAddress() == null){
            throw new RuntimeException();
        }
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
