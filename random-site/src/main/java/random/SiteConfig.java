package random;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import random.properties.RandomProperties;

/**
 * Created by DaoSui on 2015/10/14.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EntityScan
@EnableConfigurationProperties(RandomProperties.class)
public class SiteConfig {

}
