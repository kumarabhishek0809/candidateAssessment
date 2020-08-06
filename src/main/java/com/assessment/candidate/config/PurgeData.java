package com.assessment.candidate.config;

import com.assessment.candidate.entity.CandidateAssessment;
import com.assessment.candidate.entity.Question;
import com.assessment.candidate.repository.ICandidateAssessmentRepository;
import com.assessment.candidate.repository.IQuestionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PurgeData {

    private final ICandidateAssessmentRepository candidateAssessmentRepository;
    private final IQuestionRepository questionRepository;

    public PurgeData(ICandidateAssessmentRepository candidateAssessmentRepository,
                     IQuestionRepository questionRepository) {
        this.candidateAssessmentRepository = candidateAssessmentRepository;
        this.questionRepository = questionRepository;
    }

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000, initialDelay = 500)
    public void purgeAttemptedData(){
        System.out.println("purgeData purgeAttemptedData");

        ZonedDateTime past180Days = ZonedDateTime.now().plusDays(-180);

        Iterable<CandidateAssessment> allByAttemptedDateBefore = candidateAssessmentRepository.findAllByAttemptedDateBefore(past180Days);
        candidateAssessmentRepository.deleteAll(allByAttemptedDateBefore);

        System.out.println("purgeData purgeAttemptedData");

    }

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000, initialDelay = 500)
    public void purgeInvitedData(){
        System.out.println("purgeData purgeInvitedData");

        ZonedDateTime invitedLast7Days = ZonedDateTime.now().plusDays(-7);
        Iterable<CandidateAssessment> allByAttemptedDateBefore = candidateAssessmentRepository.findAllByInviteDateBeforeAndAttemptedDateIsNull(invitedLast7Days);
        candidateAssessmentRepository.deleteAll(allByAttemptedDateBefore);

        System.out.println("purgeData purgeInvitedData");

    }

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000, initialDelay = 500)
    public void purgeDisabledQuestions(){
        System.out.println("purgeData purgeDisabledQuestions");
        ZonedDateTime past180Days = ZonedDateTime.now().plusDays(-180);
        Iterable<Question> allByCreatedDateBeforeAndValid = questionRepository.findAllByCreatedDateBeforeAndValid(past180Days, false);
        questionRepository.deleteAll(allByCreatedDateBeforeAndValid);
        System.out.println("purgeData purgeDisabledQuestions");

    }
}
