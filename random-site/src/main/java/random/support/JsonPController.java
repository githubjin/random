package random.support;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * Created by DaoSui on 2015/10/17.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface JsonPController {

}
