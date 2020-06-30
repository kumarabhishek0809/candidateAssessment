package com.assessment.candidate.controller;

import com.assessment.candidate.model.EvaluationRequest;
import com.assessment.candidate.response.EvaluationResponse;
import com.assessment.candidate.service.EvaluationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping(value = "/evaluation",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EvaluationResponse>
    addAnswerToEvaluation(@RequestBody EvaluationRequest evaluationRequest){
        if(evaluationRequest == null
                || evaluationRequest.getAssessmentId() == null
        || evaluationRequest.getOptionId() == null
                || evaluationRequest.getQuestionId() == null){
            throw new RuntimeException("Not a valid request");
        }
        EvaluationResponse evaluationResponse = evaluationService.addAnswerToEvaluation(evaluationRequest);
        return new ResponseEntity<>(evaluationResponse, new HttpHeaders(), HttpStatus.OK);
    }
}
