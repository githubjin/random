package random.configs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by DaoSui on 2015/10/15.
 */

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class DemoMailConfig {

    private static final Log logger = LogFactory.getLog(DemoMailConfig.class);

    @Autowired
    private MailProperties mailProperties;

   /* @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(mailProperties.getHost());
        return mailSender;
    }*/

    @Bean
    public SimpleMailMessage templateMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperties.getFrom());
        mailMessage.setSubject("your order");
        return mailMessage;
    }

}
