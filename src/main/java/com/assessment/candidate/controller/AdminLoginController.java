package com.assessment.candidate.controller;

import com.assessment.candidate.service.EncodeDecodeService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

    @Autowired
    private EncodeDecodeService encodeDecodeService;

    @GetMapping(value = "/valdiateLogin")
    public boolean validateLoginCredentials(@RequestParam("loginId") String loginId , @RequestParam("password") String password){
        boolean loginAllowed = false;
        if(StringUtils.isNotBlank(loginId) && StringUtils.isNotBlank(password)) {
            String passwordDec = encodeDecodeService.decode(password);
            //encodedString ::::UEBzc3cwciQ=
            //decodedString ::::P@ssw0r$
            if(loginId.equalsIgnoreCase("Admin") && passwordDec.equals("P@ssw0r$")){
                loginAllowed = true;
            }
        }
        return loginAllowed;
    }

}
