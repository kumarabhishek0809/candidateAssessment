package com.assessment.candidate.service;

import com.assessment.candidate.entity.QuestionType;
import com.assessment.candidate.repository.IQuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeService {

    @Autowired
    private IQuestionTypeRepository questionTypeRepository;

    public Iterable<QuestionType> getQuestionTypes() {
        return questionTypeRepository.findAll();
    }
}
