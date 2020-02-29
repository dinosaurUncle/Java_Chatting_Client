

package me.dinosauruncle.configuration;

import me.dinosauruncle.configuration.ScreensConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ScreensConfiguration.class})
public class AppConfiguration {
    public AppConfiguration() {
    }
}
