package random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;

/**
 * Created by DaoSui on 2015/10/14.
 */
public class RandomApplication  extends SpringApplication{

    private static final Log logger = LogFactory.getLog(RandomApplication.class);

    public RandomApplication(Class<?> configClass){
        super(configClass);
    }

}
