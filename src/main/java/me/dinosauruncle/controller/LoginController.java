//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.FxmlManager;
import me.dinosauruncle.configuration.ScreensConfiguration;
import me.dinosauruncle.service.ServerConnect;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoginController {
    private Map<String, String> parameterMap = new HashMap();
    private FxmlManager fxmlManager;
    private ServerConnect serverConnect;
    private DataStructureConvert dataStructureConvert;
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

    public LoginController() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{ScreensConfiguration.class});
        this.fxmlManager = (FxmlManager)context.getBean("fxmlManager");
        this.serverConnect = (ServerConnect)context.getBean("serverConnect");
        this.dataStructureConvert = (DataStructureConvert)context.getBean("dataStructureConvert");
    }

    @FXML
    private void login(ActionEvent event) {
        this.parameterMap.put("id", this.loginId.getText());
        this.parameterMap.put("password", this.loginPw.getText());
        Map<String, String> responseMap = this.serverConnect.connectChatServer(this.dataStructureConvert.mapConvertJsonObject("login", this.parameterMap));
        serverConnect.setSession(responseMap.get("account"));
        System.out.print(ServerConnect.getSession());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("회원가입");
        alert.setHeaderText("결과");
        alert.setContentText(responseMap.get("message"));
        alert.show();
    }

    @FXML
    private void changeScene(Event event) {
        String fxmlName = "";
        String prefix = "fxml.";
        if (event.getSource() == this.searchId) {
            fxmlName = "";
        } else if (event.getSource() == this.pwReSet) {
            fxmlName = "";
        } else if (event.getSource() == this.createAccount) {
            fxmlName = prefix + "signup";
        }

        this.fxmlManager.changePage(fxmlName, (Stage)this.loginButton.getScene().getWindow());
    }

    @FXML
    private void validation(Event event) {
        if (StringUtils.isNotEmpty(this.loginId.getText()) && StringUtils.isNotEmpty(this.loginPw.getText())) {
            this.loginButton.disableProperty().setValue(false);
        }

    }
}
