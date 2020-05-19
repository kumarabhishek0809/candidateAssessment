package com.assessment.candidate.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncodeDecodeService {
    public String decode(String password) {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedString = new String(decodedBytes);
        System.out.println("decodedString ::::"+decodedString);
        return decodedString;
    }
}
