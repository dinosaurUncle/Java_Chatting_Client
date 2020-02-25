package me.dinosauruncle.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.service.ServerConnect;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class LoginController {

    private Map<String, String> parameterMap;
    public LoginController(){
        parameterMap = new HashMap<String, String>();
    }

    @FXML
    private TextField loginId;

    @FXML
    private TextField loginPw;

    @FXML
    private Button loginButton;

    @FXML
    private void login(ActionEvent event){
        parameterMap.put("id", loginId.getText());
        parameterMap.put("password", loginPw.getText());
        Map<String, String> responseMap = ServerConnect.getInstance().
                connectAfterResponse(DataStructureConvert.mapConvertJsonObject(parameterMap));
        System.out.println(responseMap);


    }

    @FXML
    private void validation(Event event){
        if (StringUtils.isNotEmpty(loginId.getText()) && StringUtils.isNotEmpty(loginPw.getText()))
            loginButton.disableProperty().setValue(false);

    }
}
