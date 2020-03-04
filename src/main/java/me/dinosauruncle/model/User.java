package me.dinosauruncle.model;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Set;

public class User {
    private String id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private boolean sessionConnect;
    public User(){
        sessionConnect = false;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUser(JSONObject jsonObject){
        Set<String> keySet = jsonObject.keySet();
        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext(); ){
            String key = iterator.next();
            if (key.equals("password")) continue;
            if (isExistValue(jsonObject.get(key))){
                switch (key) {
                    case "id" :
                        setId(String.valueOf(jsonObject.get(key)));
                        break;
                    case "name" :
                        setName(String.valueOf(jsonObject.get(key)));
                        break;
                    case "gender" :
                        setGender(String.valueOf(jsonObject.get(key)));
                        break;
                    case "email" :
                        setEmail(String.valueOf(jsonObject.get(key)));
                        break;
                    case "phone" :
                        setPhone(String.valueOf(jsonObject.get(key)));
                        break;

                }
            }
        }
        sessionConnect = true;
    }

    public boolean isExistValue(Object value){
        if (value != null){
            if (StringUtils.isNotEmpty(String.valueOf(value))) return true;
        }
        return false;
    }

    public boolean getSessionConnect(){
        return sessionConnect;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sessionConnect=" + sessionConnect +
                '}';
    }
}
