package com.assessment.candidate.controller;

import com.assessment.candidate.entity.QuestionType;
import com.assessment.candidate.service.QuestionTypeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionsTypeController {

    private final QuestionTypeService questionTypeService;

    public QuestionsTypeController(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }

    @GetMapping(value = "/questionTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<QuestionType>> getQuestionById() {
        return new ResponseEntity<>(questionTypeService.getQuestionTypes(), new HttpHeaders(), HttpStatus.OK);
    }


}
