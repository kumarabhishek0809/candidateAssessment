package com.assessment.candidate.repository;

import com.assessment.candidate.entity.UserLogin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserLoginRepository extends CrudRepository<UserLogin, Integer> {
    Optional<UserLogin> findByLoginId(String loginId);
}

