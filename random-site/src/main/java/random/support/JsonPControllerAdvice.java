package random.support;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * Created by DaoSui on 2015/10/17.
 */
@ControllerAdvice(annotations = JsonPController.class)
public class JsonPControllerAdvice extends AbstractJsonpResponseBodyAdvice {

    /*public JsonPControllerAdvice(String... queryParamNames) {
        super(queryParamNames);
    }*/

    public JsonPControllerAdvice() {
        super("callback");
    }
}
