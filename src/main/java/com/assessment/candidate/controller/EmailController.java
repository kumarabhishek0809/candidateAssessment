package com.assessment.candidate.controller;

import com.assessment.candidate.model.Email;
import com.assessment.candidate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendmail")
    public String sendmail(@RequestBody Email email) {

        emailService.sendMail(email);

        return "emailsent";
    }
}
