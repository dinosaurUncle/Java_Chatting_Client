package me.dinosauruncle.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.FxmlManager;
import me.dinosauruncle.service.ServerConnect;
import me.dinosauruncle.service.ValidationCheck;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private Map<String, String> parameterMap;
    private Map<String, String> comboBoxMap;
    private FxmlManager fxmlManager;
    public SignUpController(){
        parameterMap = new HashMap<String, String>();
        comboBoxMap = new HashMap<String, String>();
        fxmlManager = new FxmlManager();
        comboBoxMap.put("남자", "0");
        comboBoxMap.put("여자", "1");
    }

    ObservableList<String> list = FXCollections.observableArrayList("남자", "여자");


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formGender.setItems(list);
    }

    @FXML
    private void validationIdCheck(ActionEvent actionEvent){
        parameterMap.put("id", formId.getText());
        Map<String, String> responseMap = ServerConnect.getInstance().
                connectChatServer(DataStructureConvert.mapConvertJsonObject("checkId", parameterMap));
        System.out.println(responseMap);
        boolean isUse = Boolean.valueOf(responseMap.get("isUse")).booleanValue();
        System.out.println("isUse: " + isUse);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("아이디 가능여부");
        alert.setHeaderText("아이디 중복확인 결과");
        alert.setContentText(responseMap.get("message"));
        alert.show();
        System.out.println(responseMap);
        if (isUse) {
            formPassword.disableProperty().setValue(false);
            formName.disableProperty().setValue(false);
            formGender.disableProperty().setValue(false);
            formEmail.disableProperty().setValue(false);
            formPhonenumber.disableProperty().setValue(false);
            signUpButton.disableProperty().setValue(false);

        }
    }

    @FXML
    private void login(Event event){
        String fxmlName = "";
        if (event.getSource() == login || event.getSource() == signUpButton){
            fxmlName = "login.fxml";
        } else {

        }
        fxmlManager.changePage(fxmlName, (Stage)idCheck.getScene().getWindow());
    }

    @FXML
    private void signUp(Event event){
        parameterMap.clear();
        String id = formId.getText();
        String password = formPassword.getText();
        String name = formName.getText();
        String gender = formGender.getValue();
        if (gender.equals("남자")){
            gender = "0";
        } else if (gender.equals("여자")){
            gender = "1";
        } else {
            gender = "2";
        }
        String email = formEmail.getText();
        String phone = formPhonenumber.getText();
        parameterMap.put("id", id);
        parameterMap.put("password", password);
        parameterMap.put("name", name);
        parameterMap.put("gender", gender);
        parameterMap.put("email", email);
        parameterMap.put("phone", phone);
        Map<String, String> validationResultMap = ValidationCheck.validationCheck(parameterMap);
        boolean isValidationPass = Boolean.valueOf(validationResultMap.get("isPass")).booleanValue();
        if (isValidationPass){
            Map<String, String> responseMap = ServerConnect.getInstance().
                    connectChatServer(DataStructureConvert.mapConvertJsonObject("signup", parameterMap));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입");
            alert.setHeaderText("결과");
            alert.setContentText(responseMap.get("message"));
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                login(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("회원가입 유효성 검사");
            alert.setHeaderText("검토항목: " + validationResultMap.get("target"));
            alert.setContentText(validationResultMap.get("message"));
            alert.show();
            switch (validationResultMap.get("target")){
                case "password":
                    formPassword.requestFocus();
                    break;
                case "name" :
                    formName.requestFocus();
                    break;
                case "gender" :
                    formGender.requestFocus();
                    break;
                 default:
                     break;
            }


        }
    }


}
