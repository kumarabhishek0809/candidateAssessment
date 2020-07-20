package com.assessment.candidate.controller;

import com.assessment.candidate.model.AssessmentCandidateCount;
import com.assessment.candidate.model.AssessmentRequest;
import com.assessment.candidate.model.SubmitAssessmentQuestionAnswer;
import com.assessment.candidate.model.SubmitAssessmentReq;
import com.assessment.candidate.response.*;
import com.assessment.candidate.service.AssessmentsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@RestController
public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    public AssessmentsController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
    }

    @PostMapping(value = "/assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssessmentQuestionResponse> addAssessment(@RequestBody AssessmentRequest
                                                                            assessmentRequest) {
        AssessmentQuestionResponse genericResponse = assessmentsService.addUpdateAssessment(assessmentRequest);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssessmentQuestionResponse> updateAssessment(@RequestBody AssessmentRequest
                                                                               assessmentRequest) {
        if (assessmentRequest == null || assessmentRequest.getAssessmentId() == null) {
            throw new RuntimeException("Assessment Id should be blank");
        }
        AssessmentQuestionResponse genericResponse = assessmentsService.addUpdateAssessment(assessmentRequest);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/assessments", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentResponse getAssessments() {
        return assessmentsService.getAssessments();
    }

    @GetMapping(value = "/assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentDetailResponse getAssessments(@RequestParam("emailId") String emailId,
                                                   @RequestParam("assessmentId") Integer assessmentId) {
        return assessmentsService.getAssessment(assessmentId, emailId);
    }

    @GetMapping(value = "/assessmentQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentQuestionOptionsResponse getAssessmentQuestions(@RequestParam("emailId") String emailId,
                                                                    @RequestParam("assessmentId") Integer assessmentId) {
        return assessmentsService.getAssessmentQuestions(assessmentId, emailId);
    }


    @PostMapping(value = "/submitAssessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentSubmittedResponse submitAssessment(@RequestParam("emailId") String emailId,
                                                        @RequestBody SubmitAssessmentQuestionAnswer submitAssessmentQuestionAnswer
    ) throws MessagingException {
        return assessmentsService.submitAssessment(emailId, submitAssessmentQuestionAnswer);
    }

    @PostMapping(value = "/submitAssessmentMap", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssessmentSubmittedResponse submitAssessmentMap(@RequestParam("emailId") String emailId,
                                                           @RequestBody SubmitAssessmentReq submitAssessmentReq
    ) throws MessagingException {
        return assessmentsService.submitAssessment(emailId, submitAssessmentReq);
    }

    @GetMapping(value = "/candidateAssessmentCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, AssessmentCandidateCount> candidateAssessmentCount() throws MessagingException {
        return assessmentsService.candidateAssessmentCount();
    }

    @GetMapping(value = "/candidatesAssessments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssessmentCandidateCount> candidatesAssessments() throws MessagingException {
        return assessmentsService.candidatesAssessments();
    }
}
