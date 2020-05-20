package com.assessment.candidate.service;

import com.assessment.candidate.model.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Email email) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email.getToEmail());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());

        mailMessage.setFrom("johndoe@example.com");

        javaMailSender.send(mailMessage);
    }
}
