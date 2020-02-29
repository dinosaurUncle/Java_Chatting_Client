//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.FxmlManager;
import me.dinosauruncle.configuration.ScreensConfiguration;
import me.dinosauruncle.service.ServerConnect;
import me.dinosauruncle.service.ValidationCheck;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SignUpController implements Initializable {
    private Map<String, String> parameterMap = new HashMap();
    private Map<String, String> comboBoxMap = new HashMap();
    private FxmlManager fxmlManager;
    private ServerConnect serverConnect;
    private DataStructureConvert dataStructureConvert;
    private ValidationCheck validationCheck;
    ObservableList<String> list = FXCollections.observableArrayList(new String[]{"남자", "여자"});
    @FXML
    private TextField formId;
    @FXML
    private Button idCheck;
    @FXML
    private PasswordField formPassword;
    @FXML
    private TextField formName;
    @FXML
    private ComboBox<String> formGender;
    @FXML
    private TextField formEmail;
    @FXML
    private TextField formPhonenumber;
    @FXML
    private Button signUpButton;
    @FXML
    private Label login;

    public SignUpController() {
        this.comboBoxMap.put("남자", "0");
        this.comboBoxMap.put("여자", "1");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{ScreensConfiguration.class});
        this.fxmlManager = (FxmlManager)context.getBean("fxmlManager");
        this.serverConnect = (ServerConnect)context.getBean("serverConnect");
        this.dataStructureConvert = (DataStructureConvert)context.getBean("dataStructureConvert");
        this.validationCheck = (ValidationCheck)context.getBean("validationCheck");
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.formGender.setItems(this.list);
    }

    @FXML
    private void validationIdCheck(ActionEvent actionEvent) {
        this.parameterMap.put("id", this.formId.getText());
        Map<String, String> responseMap = this.serverConnect.connectAfterResponse(this.dataStructureConvert.mapConvertJsonObject("checkId", this.parameterMap));
        System.out.println(responseMap);
        boolean isUse = Boolean.valueOf((String)responseMap.get("isUse"));
        System.out.println("isUse: " + isUse);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("아이디 가능여부");
        alert.setHeaderText("아이디 중복확인 결과");
        alert.setContentText((String)responseMap.get("message"));
        alert.show();
        System.out.println(responseMap);
        if (isUse) {
            this.formPassword.disableProperty().setValue(false);
            this.formName.disableProperty().setValue(false);
            this.formGender.disableProperty().setValue(false);
            this.formEmail.disableProperty().setValue(false);
            this.formPhonenumber.disableProperty().setValue(false);
            this.signUpButton.disableProperty().setValue(false);
        }

    }

    @FXML
    private void login(Event event) {
        String fxmlName = "";
        if (event.getSource() == this.login || event.getSource() == this.signUpButton) {
            fxmlName = "login.fxml";
        }

        this.fxmlManager.changePage(fxmlName, (Stage)this.idCheck.getScene().getWindow());
    }

    @FXML
    private void signUp(Event event) {
        this.parameterMap.clear();
        String id = this.formId.getText();
        String password = this.formPassword.getText();
        String name = this.formName.getText();
        String gender = (String)this.formGender.getValue();
        if (gender.equals("남자")) {
            gender = "0";
        } else if (gender.equals("여자")) {
            gender = "1";
        } else {
            gender = "2";
        }

        String email = this.formEmail.getText();
        String phone = this.formPhonenumber.getText();
        this.parameterMap.put("id", id);
        this.parameterMap.put("password", password);
        this.parameterMap.put("name", name);
        this.parameterMap.put("gender", gender);
        this.parameterMap.put("email", email);
        this.parameterMap.put("phone", phone);
        Map<String, String> validationResultMap = this.validationCheck.validationCheck(this.parameterMap);
        boolean isValidationPass = Boolean.valueOf((String)validationResultMap.get("isPass"));
        if (isValidationPass) {
            Map<String, String> responseMap = this.serverConnect.connectAfterResponse(this.dataStructureConvert.mapConvertJsonObject("signup", this.parameterMap));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("회원가입");
            alert.setHeaderText("결과");
            alert.setContentText((String)responseMap.get("message"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.login(event);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("회원가입 유효성 검사");
            alert.setHeaderText("검토항목: " + (String)validationResultMap.get("target"));
            alert.setContentText((String)validationResultMap.get("message"));
            alert.show();
            String var14 = (String)validationResultMap.get("target");
            byte var15 = -1;
            switch(var14.hashCode()) {
                case -1249512767:
                    if (var14.equals("gender")) {
                        var15 = 2;
                    }
                    break;
                case 3373707:
                    if (var14.equals("name")) {
                        var15 = 1;
                    }
                    break;
                case 1216985755:
                    if (var14.equals("password")) {
                        var15 = 0;
                    }
            }

            switch(var15) {
                case 0:
                    this.formPassword.requestFocus();
                    break;
                case 1:
                    this.formName.requestFocus();
                    break;
                case 2:
                    this.formGender.requestFocus();
            }
        }

    }
}
