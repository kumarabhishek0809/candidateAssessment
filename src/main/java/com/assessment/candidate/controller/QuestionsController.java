package com.assessment.candidate.controller;

import com.assessment.candidate.entity.Question;
import com.assessment.candidate.model.QuestionsRequest;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.service.QuestionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionsController {

    private final QuestionService questionService;

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question>   getQuestionById(@RequestParam("id") Integer id){
        return new ResponseEntity<>(questionService.getQuestionById(id), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse>   addQuestion(@RequestBody QuestionsRequest questionsRequest){
        GenericResponse genericResponse = questionService.saveQuestion(questionsRequest);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }
}
