package random.configs;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yo on 2015/10/20.
 */

@Configuration
@EnableCaching
@AutoConfigureAfter(value = {DatabaseConfig.class})
public class CacheConfig {



}
