package random.commandLineRunners;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by DaoSui on 2015/10/15.
 */
@Order(value= Ordered.HIGHEST_PRECEDENCE - 1000)
@Component
public class DemoRunner implements CommandLineRunner {

    private final static Log logger = LogFactory.getLog(DemoRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("-----------------------------------------");
        if(args != null && args.length > 0){
            for(String arg : args){
                logger.info(arg);
            }
        }
        logger.info("-----------------------------------------");
    }
}
