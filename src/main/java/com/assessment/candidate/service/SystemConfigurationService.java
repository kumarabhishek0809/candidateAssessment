package com.assessment.candidate.service;

import com.assessment.candidate.entity.SystemConfiguration;
import com.assessment.candidate.repository.ISystemConfigurationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.assessment.candidate.CandidateApplication.CANDIDATE_CACHE;

@Service
public class SystemConfigurationService {

    public static final String ADMIN_EMAILS = "ADMIN_EMAILS";
    public static final String TEST_INVITATION_URL = "TEST_INVITATION_IP_ADDRESS";
    private final ISystemConfigurationRepository systemConfigurationRepository;

    public SystemConfigurationService(ISystemConfigurationRepository systemConfigurationRepository) {
        this.systemConfigurationRepository = systemConfigurationRepository;
    }

    @Cacheable(value = CANDIDATE_CACHE, key = " 'ADMIN_EMAILS' ")
    public List<String> adminEmails(){
        List<String> adminEmails = new ArrayList<>();

        SystemConfiguration systemConfiguration = systemConfigurationRepository.findByConfigKey(ADMIN_EMAILS).orElseThrow(() -> new RuntimeException("Admin Emails madatory"));
        String adminEmailsConcated = systemConfiguration.getConfigValue();

        adminEmails = Stream.of(adminEmailsConcated.split(",", -1))
                .collect(Collectors.toList());

        return adminEmails;
    }

    @Cacheable(value = CANDIDATE_CACHE, key = " 'TEST_INVITATION_URL' ")
    public String getCandidateIPAddress() {
        SystemConfiguration systemConfiguration = systemConfigurationRepository.findByConfigKey(TEST_INVITATION_URL)
                .orElseThrow(() -> new RuntimeException("TEST INVITATION URL MISSING"));
        return systemConfiguration.getConfigValue();
    }
}
