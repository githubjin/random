package random.controler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import random.support.ResourceNotFoundException;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by DaoSui on 2015/10/15.
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    private static final Log logger = LogFactory.getLog(DemoController.class);

//    @Autowired
//    private DemoComponent demoComponent;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    SimpleMailMessage templateMessage;
//    @Autowired
//    private DemoServices demoServices;

 /*   @RequestMapping(value = "demoName", method = RequestMethod.GET)
    public
    @ResponseBody
    String getApplicationName() {
        return demoComponent.getDemoName() + " random secret is :" + demoComponent.getSecret() +
                " demo versoin is :" + demoComponent.getDemoVersion() + " config randoms are:";
//        + demoConfig.getRandoms();
    }*/

    @RequestMapping(value = "mailInfo", method = RequestMethod.GET)
    public @ResponseBody
    String getJavaMailSenderInfo(){

        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo("1252833909@qq.com");
        msg.setText(
                "Dear " + "Y"
                        + "o"
                        + ", thank you for placing order. Your order number is "
                        + "Yoman");
        try{
            this.javaMailSender.send(msg);
        }
        catch (MailException ex) {
            logger.error("----------------------------------------------------------------");
            logger.error(ex.getMessage());
        }
        return "ok";
    }

    @RequestMapping(value = "ioEx", method = RequestMethod.GET)
    public void ioException() throws IOException{
        throw new IOException("throw ioException");
    }

    @RequestMapping(value = "ex", method = RequestMethod.GET)
    public @ResponseBody String runException() {
        int a = 1 /0;
        return "runtimeException";
    }

    @RequestMapping(value="rnf", method = RequestMethod.GET)
    public void rnf(){
        throw new ResourceNotFoundException("resource not found !");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIoException(IOException ex){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @RequestMapping(value = "authendemo", method = RequestMethod.GET)
    public ResponseEntity<SecurityContext> getSecurityInfo(){
        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<SecurityContext>(context, HttpStatus.OK);
    }
}
