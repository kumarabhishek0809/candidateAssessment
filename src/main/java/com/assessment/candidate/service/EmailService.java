package com.assessment.candidate.service;

import com.assessment.candidate.model.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Email email) throws MessagingException {
        sendMessageWithAttachment(email);
    }

    public void sendMessageWithAttachment(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email.getToEmail().toArray(new String[0]));
        helper.setSubject(email.getSubject());
        helper.setFrom(from);
        message.setText(email.getMessage() ,"utf-8", "html");
        String pathToAttachment = email.getPathToAttachment();
        if (!StringUtils.isEmpty(pathToAttachment)) {
            FileSystemResource file
                    = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("ResultOfCandidate.xls", file);
        }
        javaMailSender.send(message);
    }

}
