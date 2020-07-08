package com.assessment.candidate.controller;

import com.assessment.candidate.entity.Answer;
import com.assessment.candidate.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping(value = "/answers")
    public ResponseEntity<Iterable<Answer>> getAnswers() throws MessagingException {
        return new ResponseEntity<Iterable<Answer>>(answerService.getAnswers(), new HttpHeaders(), HttpStatus.OK);
    }

}
