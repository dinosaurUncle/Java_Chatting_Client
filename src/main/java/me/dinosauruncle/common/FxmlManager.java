package me.dinosauruncle.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlManager {

    public void changePage(String fxmlName, Stage stage){
        Stage newStaage = new Stage();
        try{

            Parent newRoot = FXMLLoader.load(PropertiesManager.getClassLoaderResource(fxmlName));

            Scene sc = new Scene(newRoot);
            newStaage.setScene(sc);
            newStaage.show();
            stage.close();
            stage.hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
