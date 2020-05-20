package com.assessment.candidate.service;

import com.assessment.candidate.entity.Assessment;
import com.assessment.candidate.repository.IAssessmentRepository;
import com.assessment.candidate.response.AssessmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentsService {

    @Autowired
    private IAssessmentRepository assessmentRepository;

    public AssessmentResponse getAssessments() {
        Iterable<Assessment> assessmentRepositoryAll = assessmentRepository.findAll();

        List<Assessment> assessments = new ArrayList<>();
        AssessmentResponse assessmentResponse = AssessmentResponse.builder().assessments(assessments).build();
        assessmentResponse.setDataAvailable(true);

        assessmentRepositoryAll.forEach(assessment -> {
            assessments.add(assessment);
        });
        return assessmentResponse;
    }
}
