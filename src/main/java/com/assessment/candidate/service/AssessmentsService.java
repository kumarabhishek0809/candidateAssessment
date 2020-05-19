package com.assessment.candidate.service;

import com.assessment.candidate.response.Assessment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentsService {
    public List<Assessment> getAssessments() {
        List<Assessment> assessments = new ArrayList<>();
        assessments.add(Assessment.builder().name("JAVA-101").duration("30").id("101").technology("JAVA").build());
        assessments.add(Assessment.builder().name("REACT-101").duration("30").id("201").technology("REACT").build());
        assessments.add(Assessment.builder().name("DEVOPS-101").duration("30").id("301").technology("DEVOPS").build());
        return assessments;
    }
}
