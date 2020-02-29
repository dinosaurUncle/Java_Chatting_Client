package me.dinosauruncle.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ValidationCheckTest {


    ValidationCheck validationCheck;

    @Test
    public void signUpValidationCheck(){
        validationCheck = new ValidationCheck();
        String tempPassword = "Pjh@5121504";
        Map<String, String> map = new HashMap<String, String>();
        map.put("password", tempPassword);
        map.put("name", "박종훈훈훈훈훈훈훈훈훈");
        map.put("gender", "0");
        System.out.println(validationCheck.validationCheck(map));


    }

}
