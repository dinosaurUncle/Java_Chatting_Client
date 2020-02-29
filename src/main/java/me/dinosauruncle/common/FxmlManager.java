//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.common;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.dinosauruncle.ChattingClientApplication;
import me.dinosauruncle.main.MainApp;

public class FxmlManager {
    public FxmlManager() {
    }

    public void changePage(String fxmlName, Stage stage) {
        Stage newStaage = new Stage();
        String path = MainApp.PATH + String.valueOf(ChattingClientApplication.propertyMap.get(fxmlName));

        try {
            Parent newRoot = (Parent)FXMLLoader.load(new URL("file:" + path));
            Scene sc = new Scene(newRoot);
            newStaage.setScene(sc);
            newStaage.show();
            stage.close();
            stage.hide();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }
}
