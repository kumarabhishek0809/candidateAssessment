package com.assessment.candidate.service;

import com.assessment.candidate.entity.Options;
import com.assessment.candidate.entity.Question;
import com.assessment.candidate.model.QuestionsRequest;
import com.assessment.candidate.repository.IAnswerRepository;
import com.assessment.candidate.repository.IQuestionRepository;
import com.assessment.candidate.repository.IQuestionTypeRepository;
import com.assessment.candidate.response.GenericResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final IQuestionRepository questionRepository;
    private final IAnswerRepository answerRepository;
    private final IQuestionTypeRepository questionTypeRepository;

    public QuestionService(IQuestionRepository questionRepository,
                           IAnswerRepository answerRepository,
                           IQuestionTypeRepository questionTypeRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionTypeRepository = questionTypeRepository;
    }

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found"));
    }

    public GenericResponse saveQuestion(QuestionsRequest questionsRequest) {

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(false);

        List<QuestionsRequest.Options> options = questionsRequest.getOptions();
        List<Options> optionsEntity = new ArrayList<>();

        if (!CollectionUtils.isEmpty(options)) {
            Question question = Question.builder().answer(
                    answerRepository
                            .findById(questionsRequest.getAnswerId()).orElseThrow(()
                            -> new RuntimeException("Incorrect Answer Id")))
                    .questionType(questionTypeRepository.findById(questionsRequest.getQuestionTypeId())
                            .orElseThrow(() -> new RuntimeException("Question Type Id")))
                    .header(questionsRequest.getHeader())
                    .technology(questionsRequest.getTechnology())
                    .options(optionsEntity)
                    .build();

            options.stream().forEach(
                    op -> optionsEntity.add(Options.builder()
                            .description(op.getDescription())
                            .question(question).build())
            );

            Question save = questionRepository.save(question);
            System.out.println(save.getId());

            genericResponse.setDataAvailable(true);
        }
        return genericResponse;
    }
}
