package random.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * Created by DaoSui on 2015/10/18.
 * Properties specific to random.
 *
 * <p>
 *     Properties are configured in the application.properties file.
 * </p>
 */
@ConfigurationProperties(prefix = "randomX", ignoreUnknownFields = false)
public class RandomProperties {

    private Security security = new Security();
    private Http http = new Http();
    private Cache cache = new Cache();

    public Http getHttp() {
        return http;
    }

    public void setHttp(Http http) {
        this.http = http;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public static class Http {
        private Cache cache = new Cache();

        public Cache getCache() {
            return cache;
        }

        public void setCache(Cache cache) {
            this.cache = cache;
        }

        public static class Cache {
            private  int timeToLiveInDays = 31;

            public int getTimeToLiveInDays() {
                return timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Security {

        private Rememberme rememberme = new Rememberme();

        private Authentication authentication = new Authentication();

        public Rememberme getRememberme() {
            return rememberme;
        }

        public void setRememberme(Rememberme rememberme) {
            this.rememberme = rememberme;
        }

        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class Authentication {

            private Jwt jwt = new Jwt();

            public Jwt getJwt() {
                return jwt;
            }

            private Xauth xauth = new Xauth();

            public Xauth getXauth() {
                return xauth;
            }

            public void setJwt(Jwt jwt) {
                this.jwt = jwt;
            }

            public void setXauth(Xauth xauth) {
                this.xauth = xauth;
            }

            public static class Jwt {

                private String issuer;
                private String secret;
                private String tokenValidityInMinutes = "30f";
                private String notBeforeMinutesInThePast = "0f";
                private String allowedClockSkewInSeconds = "30";

                public String getIssuer() {
                    return issuer;
                }

                public void setIssuer(String issuer) {
                    this.issuer = issuer;
                }

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public float getTokenValidityInMinutes() {
                    return Float.parseFloat(tokenValidityInMinutes);
                }

                public void setTokenValidityInMinutes(String tokenValidityInMinutes) {
                    this.tokenValidityInMinutes = tokenValidityInMinutes;
                }

                public float getNotBeforeMinutesInThePast() {
                    return Float.parseFloat(notBeforeMinutesInThePast);
                }

                public void setNotBeforeMinutesInThePast(String notBeforeMinutesInThePast) {
                    this.notBeforeMinutesInThePast = notBeforeMinutesInThePast;
                }

                public int getAllowedClockSkewInSeconds() {
                    return Integer.parseInt(allowedClockSkewInSeconds);
                }

                public void setAllowedClockSkewInSeconds(String allowedClockSkewInSeconds) {
                    this.allowedClockSkewInSeconds = allowedClockSkewInSeconds;
                }
            }

            public static class Xauth {

                private String secret;

                private String tokenValidityInSeconds = "1800";

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public int getTokenValidityInSeconds() {
                    return Integer.parseInt(tokenValidityInSeconds);
                }

                public void setTokenValidityInSeconds(String tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }
            }
        }
        public static class Rememberme {

            @NotNull
            private String key;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public static class Cache {

        private int timeToLiveSeconds = 3600;

        private Ehcache ehcache = new Ehcache();

        public int getTimeToLiveSeconds() {
            return timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(int timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public void setEhcache(Ehcache ehcache) {
            this.ehcache = ehcache;
        }

        public Ehcache getEhcache() {
            return ehcache;
        }

        public static class Ehcache {

            private String maxBytesLocalHeap = "16M";

            public String getMaxBytesLocalHeap() {
                return maxBytesLocalHeap;
            }

            public void setMaxBytesLocalHeap(String maxBytesLocalHeap) {
                this.maxBytesLocalHeap = maxBytesLocalHeap;
            }
        }
    }
}
