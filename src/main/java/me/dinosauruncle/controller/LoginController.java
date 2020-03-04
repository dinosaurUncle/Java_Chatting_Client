package me.dinosauruncle.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.FxmlManager;
import me.dinosauruncle.service.ServerConnect;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class LoginController {

    private Map<String, String> parameterMap;
    private FxmlManager fxmlManager;
    public LoginController(){
        parameterMap = new HashMap<String, String>();
        fxmlManager = new FxmlManager();
    }

    @FXML
    private TextField loginId;

    @FXML
    private PasswordField loginPw;

    @FXML
    private Button loginButton;

    @FXML
    private Label searchId;

    @FXML
    private Label pwReSet;

    @FXML
    private Label createAccount;

    @FXML
    private void login(ActionEvent event){
        parameterMap.put("id", loginId.getText());
        parameterMap.put("password", loginPw.getText());
        Map<String, String> responseMap = ServerConnect.getInstance().
                connectChatServer(DataStructureConvert.mapConvertJsonObject("login", parameterMap));
        ServerConnect.getInstance().setSession(responseMap.get("account"));
        System.out.print(ServerConnect.getSession());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("회원가입");
        alert.setHeaderText("결과");
        alert.setContentText(responseMap.get("message"));
        alert.show();



    }
    @FXML
    private void changeScene(Event event){
        String fxmlName = "";

        if (event.getSource() == searchId){
            fxmlName = "";
        } else if (event.getSource() == pwReSet){
            fxmlName = "";
        } else if (event.getSource() == createAccount){
            fxmlName = "signup.fxml";
        } else {

        }
        fxmlManager.changePage(fxmlName, (Stage)loginButton.getScene().getWindow());

    }

    @FXML
    private void validation(Event event){
        if (StringUtils.isNotEmpty(loginId.getText()) && StringUtils.isNotEmpty(loginPw.getText()))
            loginButton.disableProperty().setValue(false);

    }
}
