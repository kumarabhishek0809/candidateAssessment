package com.assessment.candidate.repository;

import com.assessment.candidate.entity.UserLoginHistory;
import org.springframework.data.repository.CrudRepository;

public interface IUserLoginHistoryRepository extends CrudRepository<UserLoginHistory, Integer> {
}

