package me.dinosauruncle.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.dinosauruncle.common.PropertiesManager;

import java.net.URL;

public class MainApp extends Application {

    public MainApp(){
        PropertiesManager.initResouceRecode();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url =MainApp.class.getClassLoader().getResource("me/dinosauruncle/style/styles.css");
        //Parent root = FXMLLoader.load(MainApp.class.getClassLoader().getResource("me/dinosauruncle/login/login.fxml"));
        Parent root = FXMLLoader.load(PropertiesManager.getClassLoaderResource("login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(PropertiesManager.getClassLoaderResource("styles.css").toExternalForm());

        stage.setTitle("JavaFx and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
