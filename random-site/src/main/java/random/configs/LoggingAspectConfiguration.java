package random.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import random.Constants;
import random.RandomProfiles;
import random.aop.logging.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(RandomProfiles.DEVELOPMENT)
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
