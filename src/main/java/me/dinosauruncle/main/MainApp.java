//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.main;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import me.dinosauruncle.ChattingClientApplication;
import me.dinosauruncle.configuration.AppConfiguration;
import me.dinosauruncle.configuration.ScreensConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainApp extends Application {
    public static String PATH = null;

    public MainApp() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(new Class[]{AppConfiguration.class});
        String path = this.getClass().getResource(String.valueOf(ChattingClientApplication.propertyMap.get("fxml.login"))).getPath();
        PATH = path.replace("fxml/login.fxml", "");
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:" + path));
        Parent rootNode = (Parent)fxmlLoader.load();
        ScreensConfiguration screens = (ScreensConfiguration)context.getBean(ScreensConfiguration.class);
        screens.setStage(stage);
        screens.showScreen(rootNode);
    }
}
