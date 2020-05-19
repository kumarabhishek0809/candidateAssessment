package com.assessment.candidate.controller;

import com.assessment.candidate.response.Assessment;
import com.assessment.candidate.service.AssessmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssessmentsController {

    @Autowired
    private AssessmentsService assessmentsService;


    @GetMapping(value = "/assessments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Assessment> getAssessments(){
        return assessmentsService.getAssessments();
    }
}
