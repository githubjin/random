package random.security.xauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import random.properties.RandomProperties;
import random.security.xauth.XauthTokenProvider;

/**
* Configures x-auth-token security.
*/
@Configuration
public class XAuthConfiguration {

    @Bean
    public XauthTokenProvider tokenProvider(RandomProperties randomProperties) {
        String secret = randomProperties.getSecurity().getAuthentication().getXauth().getSecret();
        int validityInSeconds = randomProperties.getSecurity().getAuthentication().getXauth().getTokenValidityInSeconds();
        return new XauthTokenProvider(secret, validityInSeconds);
    }
}
