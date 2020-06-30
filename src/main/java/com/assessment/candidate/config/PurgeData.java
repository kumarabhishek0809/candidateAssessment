package com.assessment.candidate.config;

import com.assessment.candidate.entity.CandidateAssessment;
import com.assessment.candidate.repository.ICandidateAssessmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PurgeData {

    private final ICandidateAssessmentRepository candidateAssessmentRepository;

    public PurgeData(ICandidateAssessmentRepository candidateAssessmentRepository) {
        this.candidateAssessmentRepository = candidateAssessmentRepository;
    }

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000, initialDelay = 500)
    public void purgeData(){
        System.out.println("purgeData Cache");

        ZonedDateTime past180Days = ZonedDateTime.now().plusDays(-180);

        Iterable<CandidateAssessment> allByAttemptedDateBefore = candidateAssessmentRepository.findAllByAttemptedDateBefore(past180Days);
        candidateAssessmentRepository.deleteAll(allByAttemptedDateBefore);

        System.out.println("purgeData Cache");

    }
}
