package me.dinosauruncle.service;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {

    public static Map<String, String> validationCheck(Map<String, String> parameterMap){
        System.out.println("##############ValidationCheck##################");
        Map<String, String> validationResultMap = new HashMap<String, String>();
        boolean isCatch = false;
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet){
            if (key.equals("password")){
            /*
                1. 영문(대소문자 구분), 숫자, 특수문자 조합
                2. 9~12자리 사이 문자
             */

                String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
                Matcher matcher = Pattern.compile(pwPattern).matcher(parameterMap.get(key));
                if (!matcher.matches()){
                    stringBuffer.append("비밀번호가 조건에 부합하지 않습니다.");
                    stringBuffer.append("아래 조건에 맞추어서 비밀번호를 재입력 해주세요!");
                    stringBuffer.append("1. 영문(대소문자 구분), 숫자, 특수문자 조합");
                    stringBuffer.append("2. 9~12자리 사이 문자");
                    validationResultMap.put("message", stringBuffer.toString());
                    validationResultMap.put("isPass", "false");
                    validationResultMap.put("target", "password");
                    isCatch = true;
                    break;
                }

                pwPattern = "(.)\\1\\1\\1";
                Matcher matcher2 = Pattern.compile(pwPattern).matcher(parameterMap.get(key));
                if(matcher2.find()){
                    stringBuffer.append("비밀번호가 조건에 부합하지 않습니다.");
                    stringBuffer.append("아래 조건에 맞추어서 비밀번호를 재입력 해주세요!");
                    stringBuffer.append("- 같은 문자 4개 이상 사용 불가");
                    validationResultMap.put("message", stringBuffer.toString());
                    validationResultMap.put("isPass", "false");
                    validationResultMap.put("target", "password");
                    isCatch = true;
                    break;
                }

            } else if (key.equals("name")){
                String namePattern = "^[ㄱ-ㅎㅏ-ㅣ가-힣]*$";
                Matcher nameMatcher = Pattern.compile(namePattern).matcher(parameterMap.get(key));
                if(!nameMatcher.matches() ||
                        !(parameterMap.get(key).length() >= 2 && parameterMap.get(key).length() <= 8)){
                    stringBuffer.append("이름은 반드시 2~8자 한글이어야 합니다");
                    validationResultMap.put("message", stringBuffer.toString());
                    validationResultMap.put("isPass", "false");
                    validationResultMap.put("target", "name");
                    isCatch = true;
                    break;
                }




            } else if (key.equals("gender")){
                if (parameterMap.get("gender").equals("2")){
                    stringBuffer.append("성별을 선택해 주세요(남자, 여자)");
                    validationResultMap.put("message", stringBuffer.toString());
                    validationResultMap.put("isPass", "false");
                    validationResultMap.put("target", "gender");
                }

            }
        }
        if (!isCatch) validationResultMap.put("isPass", "true");
        return validationResultMap;
    }
}
