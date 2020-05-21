package com.assessment.candidate.controller;

import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentResponse;
import com.assessment.candidate.service.AssessmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssessmentsController {

    @Autowired
    private AssessmentsService assessmentsService;


    @GetMapping(value = "/assessments", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentResponse getAssessments(){
        return assessmentsService.getAssessments();
    }

    @GetMapping(value = "/assessment/{assessmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentDetailResponse getAssessments(@PathVariable("assessmentId") Integer assessmentId, @RequestParam("emailId") String emailId){
        return assessmentsService.getAssessment(assessmentId,emailId);
    }
}
