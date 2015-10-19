package random.commandLineRunners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by DaoSui on 2015/10/15.
 */
@Order(value= Ordered.HIGHEST_PRECEDENCE - 100)
@Component
public class DemoTowRunner implements CommandLineRunner {

    private final static Log logger = LogFactory.getLog(DemoTowRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("--------------------------DemoTowRunner--------------------------------------is running !");
    }
}
