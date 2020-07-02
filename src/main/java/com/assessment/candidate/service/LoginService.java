package com.assessment.candidate.service;

import com.assessment.candidate.entity.UserLogin;
import com.assessment.candidate.entity.UserLoginHistory;
import com.assessment.candidate.model.LoginRequest;
import com.assessment.candidate.model.LoginResponse;
import com.assessment.candidate.repository.IUserLoginHistoryRepository;
import com.assessment.candidate.repository.IUserLoginRepository;
import com.assessment.candidate.util.EmailValidation;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final IUserLoginHistoryRepository userLoginHistoryRepository;
    private final IUserLoginRepository userLoginRepository;

    public LoginService(IUserLoginHistoryRepository userLoginHistoryRepository,
                        IUserLoginRepository userLoginRepository) {
        this.userLoginHistoryRepository = userLoginHistoryRepository;
        this.userLoginRepository = userLoginRepository;
    }

    public boolean validateUserLogin(String loginId, String password, String remoteAddr) {

        boolean validateLogin = false;

        UserLogin userLogin = userLoginRepository.findByLoginId(loginId).orElseThrow(() ->
                new RuntimeException("User does not exits with login id " + loginId));

        validateLogin = userLogin.getPassword().equalsIgnoreCase(password);

        if(validateLogin){
            userLoginHistoryRepository.save(
                    UserLoginHistory.builder()
                    .loginId(loginId)
                    .password(password)
                    .remoteAddr(remoteAddr)
                    .build()
            );
        }
        return validateLogin;

    }

    public LoginResponse addLoginUser(LoginRequest loginRequest) {

        boolean validEmail = EmailValidation.isValidEmail(loginRequest.getLoginId());

        if(!validEmail){
            throw new RuntimeException("Invalid Login email id");
        }

        UserLogin userLogin = userLoginRepository.
                findByLoginId(loginRequest.getLoginId()).orElse( UserLogin.builder()
                .loginId(loginRequest.getLoginId()).build());

        userLogin.setPassword(loginRequest.getPassword());
        UserLogin login = userLoginRepository.save(userLogin);
        LoginResponse loginResponse = LoginResponse.builder().loginId(login.getLoginId()).build();
        loginResponse.setDataAvailable(true);
        return loginResponse;
    }
}
