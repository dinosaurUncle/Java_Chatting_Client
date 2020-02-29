//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {
    public ValidationCheck() {
    }

    public Map<String, String> validationCheck(Map<String, String> parameterMap) {
        System.out.println("##############ValidationCheck##################");
        Map<String, String> validationResultMap = new HashMap();
        boolean isCatch = false;
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keySet = parameterMap.keySet();
        Iterator var6 = keySet.iterator();

        while(var6.hasNext()) {
            String key = (String)var6.next();
            String namePattern;
            Matcher nameMatcher;
            if (key.equals("password")) {
                namePattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
                nameMatcher = Pattern.compile(namePattern).matcher((CharSequence)parameterMap.get(key));
                if (!nameMatcher.matches()) {
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

                namePattern = "(.)\\1\\1\\1";
                Matcher matcher2 = Pattern.compile(namePattern).matcher((CharSequence)parameterMap.get(key));
                if (matcher2.find()) {
                    stringBuffer.append("비밀번호가 조건에 부합하지 않습니다.");
                    stringBuffer.append("아래 조건에 맞추어서 비밀번호를 재입력 해주세요!");
                    stringBuffer.append("- 같은 문자 4개 이상 사용 불가");
                    validationResultMap.put("message", stringBuffer.toString());
                    validationResultMap.put("isPass", "false");
                    validationResultMap.put("target", "password");
                    isCatch = true;
                    break;
                }
            } else if (key.equals("name")) {
                namePattern = "^[ㄱ-ㅎㅏ-ㅣ가-힣]*$";
                nameMatcher = Pattern.compile(namePattern).matcher((CharSequence)parameterMap.get(key));
                if (!nameMatcher.matches() || ((String)parameterMap.get(key)).length() < 2 || ((String)parameterMap.get(key)).length() > 8) {
                    stringBuffer.append("이름은 반드시 2~8자 한글이어야 합니다");
                    validationResultMap.put("message", stringBuffer.toString());
                    validationResultMap.put("isPass", "false");
                    validationResultMap.put("target", "name");
                    isCatch = true;
                    break;
                }
            } else if (key.equals("gender") && ((String)parameterMap.get("gender")).equals("2")) {
                stringBuffer.append("성별을 선택해 주세요(남자, 여자)");
                validationResultMap.put("message", stringBuffer.toString());
                validationResultMap.put("isPass", "false");
                validationResultMap.put("target", "gender");
            }
        }

        if (!isCatch) {
            validationResultMap.put("isPass", "true");
        }

        return validationResultMap;
    }
}
