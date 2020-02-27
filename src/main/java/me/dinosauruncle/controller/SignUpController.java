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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    private Label login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formGender.setItems(list);
    }

    @FXML
    private void validationIdCheck(ActionEvent actionEvent){
        parameterMap.put("id", formId.getText());
        Map<String, String> responseMap = ServerConnect.getInstance().
                connectAfterResponse(DataStructureConvert.mapConvertJsonObject("checkId", parameterMap));
    }

    @FXML
    private void login(Event event){
        String fxmlName = "";
        if (event.getSource() == login){
            fxmlName = "login.fxml";
        } else {

        }
        fxmlManager.changePage(fxmlName, (Stage)idCheck.getScene().getWindow());
    }


}
