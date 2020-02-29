//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle.configuration;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.dinosauruncle.common.DataStructureConvert;
import me.dinosauruncle.common.FxmlManager;
import me.dinosauruncle.common.IOStreamUtils;
import me.dinosauruncle.service.ServerConnect;
import me.dinosauruncle.service.ValidationCheck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
public class ScreensConfiguration {
    private Stage stage;

    public ScreensConfiguration() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showScreen(Parent screen) {
        this.stage.setScene(new Scene(screen));
        this.stage.setTitle("JavaFx and Gradle");
        this.stage.show();
    }

    @Bean
    @Scope("prototype")
    DataStructureConvert dataStructureConvert() {
        return new DataStructureConvert();
    }

    @Bean
    @Scope("prototype")
    FxmlManager fxmlManager() {
        return new FxmlManager();
    }

    @Bean
    @Scope("prototype")
    IOStreamUtils ioStreamUtils() {
        return new IOStreamUtils();
    }

    @Bean
    @Scope("prototype")
    ServerConnect serverConnect() {
        return new ServerConnect();
    }

    @Bean
    @Scope("prototype")
    ValidationCheck validationCheck() {
        return new ValidationCheck();
    }
}
