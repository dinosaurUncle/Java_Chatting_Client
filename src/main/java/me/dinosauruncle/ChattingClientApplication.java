//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.dinosauruncle;

import java.util.Map;
import me.dinosauruncle.main.MainApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
public class ChattingClientApplication implements ApplicationRunner {
    @Autowired
    ApplicationContext context;
    public static Map<String, Object> propertyMap = null;

    public ChattingClientApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ChattingClientApplication.class, args);
    }

    public void run(ApplicationArguments args) throws Exception {
        ConfigurableEnvironment env = (ConfigurableEnvironment)this.context.getEnvironment();
        MutablePropertySources mutablePropertySources = env.getPropertySources();
        PropertySource propertySource = mutablePropertySources.get("applicationConfig: [classpath:/application.properties]");
        propertyMap = (Map)propertySource.getSource();
        MainApp.main((String[])null);
    }
}
