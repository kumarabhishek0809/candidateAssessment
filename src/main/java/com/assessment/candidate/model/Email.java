package com.assessment.candidate.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
    private String toEmail;
    private String subject;
    private String message;

    private String pathToAttachment;
}
