package com.assessment.candidate.repository;

import com.assessment.candidate.entity.SystemConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ISystemConfigurationRepository extends CrudRepository<SystemConfiguration, Integer> {
    Optional<SystemConfiguration> findByConfigKey(String configKey);
}
