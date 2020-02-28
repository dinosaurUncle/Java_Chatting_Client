package me.dinosauruncle.service;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidationCheckTest {

    @Test
    public void signUpValidationCheck(){
        String tempPassword = "Pjh@5121504";
        Map<String, String> map = new HashMap<String, String>();
        map.put("password", tempPassword);
        map.put("name", "박종훈훈훈훈훈훈훈훈훈");
       map.put("gender", "0");
        System.out.println(ValidationCheck.validationCheck(map));


    }

}
