package com.assessment.candidate.controller;

import com.assessment.candidate.model.AssessmentCandidateCount;
import com.assessment.candidate.model.AssessmentRequest;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.response.AssessmentDetailResponse;
import com.assessment.candidate.response.AssessmentResponse;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.service.AssessmentsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    public AssessmentsController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
    }

    @PostMapping(value = "/assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> addAssessment(@RequestBody AssessmentRequest assessmentRequest){
        GenericResponse genericResponse = assessmentsService.addUpdateAssessment(assessmentRequest);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> updateAssessment(@RequestBody AssessmentRequest assessmentRequest){
        if(assessmentRequest == null || assessmentRequest.getAssessmentId() != null){
            throw new RuntimeException("Assessment Id should be blank");
        }
        GenericResponse genericResponse = assessmentsService.addUpdateAssessment(assessmentRequest);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }

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
    public Map<String, AssessmentCandidateCount> candidateAssessmentCount() throws MessagingException {
        return assessmentsService.candidateAssessmentCount();
    }
}
