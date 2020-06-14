package com.assessment.candidate.controller;

import com.assessment.candidate.model.AssessmentCandidateCount;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentResponse;
import com.assessment.candidate.service.AssessmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class AssessmentsController {

    @Autowired
    private AssessmentsService assessmentsService;


    @GetMapping(value = "/assessments", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentResponse getAssessments(){
        return assessmentsService.getAssessments();
    }

    @GetMapping(value = "/assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentDetailResponse getAssessments(@RequestParam("emailId") String emailId ,
                                                   @RequestParam("assessmentId") Integer assessmentId){
        return assessmentsService.getAssessment(assessmentId,emailId);
    }


    @PostMapping(value = "/submitAssessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentDetailResponse submitAssessment(@RequestParam("emailId") String emailId,
                                                     @RequestBody SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer
                                                   ) throws MessagingException {
        return assessmentsService.submitAssessment(emailId,submitAssessmentQuestionAnswer);
    }

    @GetMapping(value = "/candidateAssessmentCount" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssessmentCandidateCount> candidateAssessmentCount() throws MessagingException {
        return assessmentsService.candidateAssessmentCount();
    }
}
