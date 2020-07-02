package com.assessment.candidate.controller;

import com.assessment.candidate.model.LoginRequest;
import com.assessment.candidate.model.LoginResponse;
import com.assessment.candidate.service.LoginService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/valdiateLogin")
    public boolean validateLoginCredentials(
            @RequestParam("loginId") String loginId ,
            @RequestParam("password") String password,
            HttpServletRequest request){
        boolean loginAllowed = true;
        System.out.println("loginId" +loginId);
        System.out.println("password" +password);
        System.out.println("request" +request.getRemoteAddr());
        if(StringUtils.isNotBlank(loginId) && StringUtils.isNotBlank(password)) {
            loginService.validateUserLogin(loginId,password,request.getRemoteAddr());
        }
        return false;
    }


    @PostMapping(value = "/loginUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> addAssessment(@RequestBody LoginRequest
                                                                            loginRequest){
        LoginResponse genericResponse = loginService.addLoginUser(loginRequest);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
    }



}
