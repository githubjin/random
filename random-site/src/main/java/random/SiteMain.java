package random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by DaoSui on 2015/10/14.
 */

public class SiteMain {

    private static final Logger logger = LoggerFactory.getLogger(SiteMain.class);

    public  static void main(String[] args) throws UnknownHostException {
        RandomApplication springApplication = new RandomApplication(SiteConfig.class);
//        springApplication.addListeners();
//        springApplication.setWebEnvironment();
//        springApplication.setApplicationContextClass();
//        springApplication.setAddCommandLineProperties();
        springApplication.setShowBanner(false);
        SimpleCommandLinePropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(springApplication, commandLinePropertySource);
        ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
        consoleApplicationSetupInfo(environment);
    }

    /**
     * 输出
     * @param env
     */
    private static void consoleApplicationSetupInfo(Environment env) throws UnknownHostException {
        logger.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    /**
     * 如果没有设置profile,默认设置为"dev"
     * @param app
     * @param source
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if(!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            app.setAdditionalProfiles(RandomProfiles.DEVELOPMENT);
        }
    }
}
