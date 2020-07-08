package com.assessment.candidate.service;

import com.assessment.candidate.entity.Answer;
import com.assessment.candidate.repository.IAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private IAnswerRepository answerRepository;

    public Iterable<Answer> getAnswers() {
        return answerRepository.findAll();
    }
}
