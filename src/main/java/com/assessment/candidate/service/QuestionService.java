package com.assessment.candidate.service;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.entity.EvaluationQuestionAnswer;
import com.assessment.candidate.entity.Options;
import com.assessment.candidate.entity.Question;
import com.assessment.candidate.model.QuestionsRequest;
import com.assessment.candidate.repository.*;
import com.assessment.candidate.response.EvaluationResponse;
import com.assessment.candidate.response.GenericResponse;
import com.assessment.candidate.response.QuestionAddResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final IQuestionRepository questionRepository;
    private final IAnswerRepository answerRepository;
    private final IQuestionTypeRepository questionTypeRepository;
    private final IAssessmentRepository assessmentRepository;
    private final IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository;

    public QuestionService(IQuestionRepository questionRepository,
                           IAnswerRepository answerRepository,
                           IQuestionTypeRepository questionTypeRepository,
                           IAssessmentRepository assessmentRepository,
                           IEvaluationQuestionAnswerRepository evaluationQuestionAnswerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionTypeRepository = questionTypeRepository;
        this.assessmentRepository = assessmentRepository;
        this.evaluationQuestionAnswerRepository = evaluationQuestionAnswerRepository;
    }

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question Not Found"));
    }


    public QuestionAddResponse questionOptionsAssessment(QuestionsRequest questionsRequest) {
        QuestionAddResponse genericResponse = new QuestionAddResponse();
        genericResponse.setDataAvailable(false);

        List<QuestionsRequest.Options> options = questionsRequest.getOptions();
        List<Options> optionsEntity = new ArrayList<>();

        if (!CollectionUtils.isEmpty(options)) {

            if (questionsRequest.getQuestionTypeId().equals(new Integer(1))) {
                long count = options.stream().filter(op -> op.isAnswerOption()).count();
                if (count != 1) {
                    throw new RuntimeException("Only One Answer is correct");
                }
            }

            Optional<Question> byHeader = questionRepository.findByHeader(StringUtils.trimWhitespace(questionsRequest.getHeader()));

            if (byHeader.isPresent()) {
                throw new RuntimeException("Question Already Present");
            }

            Question question = Question.builder().answer(
                    answerRepository
                            .findById(questionsRequest.getAnswerId()).orElseThrow(()
                            -> new RuntimeException("Incorrect Answer Id")))
                    .questionType(questionTypeRepository.findById(questionsRequest.getQuestionTypeId())
                            .orElseThrow(() -> new RuntimeException("Question Type Id")))
                    .header(StringUtils.trimWhitespace(questionsRequest.getHeader()))
                    .technology(questionsRequest.getTechnology())
                    .options(optionsEntity)
                    .build();

            options.stream().filter(ops -> StringUtils.hasText(StringUtils.trimWhitespace(ops.getDescription()))).forEach(
                    op -> optionsEntity.add(Options.builder()
                            .description(StringUtils.trimWhitespace(op.getDescription()))
                            .question(question).build())
            );


            Question questionSave = questionRepository.save(question);
            genericResponse.setQuestion(questionSave);
            System.out.println(question.getId());

            Optional<QuestionsRequest.Options> correctAnswer = options.stream().filter(op -> op.isAnswerOption()).findFirst();

            if (correctAnswer.isPresent()) {
                QuestionsRequest.Options optionAns = correctAnswer.get();
                Options answerOption = questionSave.getOptions().stream().filter(op ->
                        op.getDescription().equalsIgnoreCase(optionAns.getDescription())).findAny()
                        .orElseThrow(() -> new RuntimeException("Please select correct answer option"));

                //assessment
                if (questionsRequest != null && questionSave != null) {
                    List<Integer> assessmentIds = questionsRequest.getAssessmentIds();
                    Iterable<Assessment> assessments = assessmentRepository.findAllById(assessmentIds);
                    assessments.forEach(
                            assementReq -> {
                                List<Question> questions = assementReq.getQuestions();
                                if (CollectionUtils.isEmpty(questions)) {
                                    questions = new ArrayList<>();
                                }
                                questions.add(questionSave);
                                assementReq.setQuestions(questions);
                                Assessment assessment = assessmentRepository.save(assementReq);

                                //Correct option.
                                EvaluationQuestionAnswer evaluationQuestionAnswer = new EvaluationQuestionAnswer();
                                evaluationQuestionAnswer.setMarks(questionsRequest.getMarks());
                                evaluationQuestionAnswer.setOptions(answerOption);
                                evaluationQuestionAnswer.setQuestion(question);
                                EvaluationQuestionAnswer questionAnswer = evaluationQuestionAnswerRepository.save(evaluationQuestionAnswer);


                                EvaluationResponse evaluationResponse = new EvaluationResponse();
                                evaluationResponse.setDataAvailable(true);
                                evaluationResponse.setId(questionAnswer.getId());
                                evaluationResponse.setMarks(questionAnswer.getMarks());
                                evaluationResponse.setOptions(questionAnswer.getOptions());
                                evaluationResponse.setQuestion(questionAnswer.getQuestion().getHeader());
                                genericResponse.setEvaluationResponse(evaluationResponse);

                            });

                }
            }
            genericResponse.setDataAvailable(true);
        }
        return genericResponse;
    }

    public QuestionAddResponse saveUpdateQuestion(QuestionsRequest questionsRequest) {

        QuestionAddResponse genericResponse = new QuestionAddResponse();
        genericResponse.setDataAvailable(false);

        List<QuestionsRequest.Options> options = questionsRequest.getOptions();
        List<Options> optionsEntity = new ArrayList<>();
        Question question = null;
        if (!CollectionUtils.isEmpty(options)) {
            if (questionsRequest.getQuestionId() == null) {
                question = Question.builder().answer(
                        answerRepository
                                .findById(questionsRequest.getAnswerId()).orElseThrow(()
                                -> new RuntimeException("Incorrect Answer Id")))
                        .questionType(questionTypeRepository.findById(questionsRequest.getQuestionTypeId())
                                .orElseThrow(() -> new RuntimeException("Question Type Id")))
                        .header(StringUtils.trimWhitespace(questionsRequest.getHeader()))
                        .technology(questionsRequest.getTechnology())
                        .options(optionsEntity)
                        .build();

                Question finalQuestion = question;
                options.stream().forEach(
                        op -> optionsEntity.add(Options.builder()
                                .description(op.getDescription())
                                .question(finalQuestion).build())
                );
            }
        }
        if (questionsRequest.getQuestionId() != null) {
            question = questionRepository.findById(questionsRequest.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Not a Valid Question Id  " + questionsRequest.getQuestionId()));
            question.setValid(Optional.ofNullable(questionsRequest.isValid()).orElse(question.isValid()));
        }
        Question questionSave = questionRepository.save(question);
        genericResponse.setQuestion(questionSave);
        System.out.println(question.getId());

        genericResponse.setDataAvailable(true);
        return genericResponse;
}

    public GenericResponse deleteQuestionById(Integer id) {
        questionRepository.deleteById(id);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setDataAvailable(true);
        return genericResponse;
    }
}
