package random.security.Jwt;

import org.jose4j.lang.JoseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import random.properties.RandomProperties;
import random.security.Jwt.JwtTokenProvider;

/**
 * Created by DaoSui on 2015/10/18.
 * Configures jwt-token security.
 */
//@Configuration
public class JwtTokenConfiguration {

//    @Bean
//    public JwtTokenProvider tokenProvider(RandomProperties randomProperties) throws JoseException {
//        String secret = randomProperties.getSecurity().getAuthentication().getJwt().getSecret();
//        float validityInMinutes = randomProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInMinutes();
//        float notBeforeMinutesInThePast = randomProperties.getSecurity().getAuthentication().getJwt().getNotBeforeMinutesInThePast();
//        String issuer = randomProperties.getSecurity().getAuthentication().getJwt().getIssuer();
//        int allowedClockSkewInSeconds = randomProperties.getSecurity().getAuthentication().getJwt().getAllowedClockSkewInSeconds();
//
//        return new JwtTokenProvider(secret, validityInMinutes, notBeforeMinutesInThePast, issuer, allowedClockSkewInSeconds);
//    }
}
