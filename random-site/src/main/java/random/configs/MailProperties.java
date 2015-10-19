package random.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Yo on 2015/10/15.
 */
@ConfigurationProperties(locations = "classpath:mail.properties",ignoreUnknownFields = false, prefix = "appMail")
public class MailProperties {
    public static class Smtp {
        private String auth;
        private String starttlsEnable;

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getStarttlsEnable() {
            return starttlsEnable;
        }

        public void setStarttlsEnable(String starttlsEnable) {
            this.starttlsEnable = starttlsEnable;
        }
    }

    private String host;
    private String port;
    private String from;
    private String username;
    private String password;
    private Smtp smtp;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getFrom() {
        return from;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSmtp(Smtp smtp) {
        this.smtp = smtp;
    }

    public Smtp getSmtp() {
        return smtp;
    }
}
