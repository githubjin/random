package random.exitCodeGenerator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ExitCodeGenerator;

/**
 * Created by DaoSui on 2015/10/15.
 */
public class DemoExitCodeGenerator implements ExitCodeGenerator {

    private final static Log logger = LogFactory.getLog(DemoExitCodeGenerator.class);

    @Override
    public int getExitCode() {
        logger.info("----------------------application end !-----------------------------------");
        return 0;
    }
}
