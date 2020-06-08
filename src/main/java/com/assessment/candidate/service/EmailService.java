package com.assessment.candidate.service;

import com.assessment.candidate.model.Email;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Email email) {
        System.out.println("Inside Send Email");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getToEmail());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        mailMessage.setFrom("noreply@example.com");
        javaMailSender.send(mailMessage);
        System.out.println("Exit Send Email");
    }

    public void sendMessageWithAttachment(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email.getToEmail());
        helper.setSubject(email.getSubject());
        helper.setText(email.getMessage());
        FileSystemResource file
                = new FileSystemResource(new File(email.getPathToAttachment()));
        helper.addAttachment("ResultOfCandidate.xls", file);
        javaMailSender.send(message);
    }

}
