package random.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.CharacterEncodingFilter;
import random.support.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by Yo on 2015/10/20.
 */
@Configuration
//@ControllerAdvice
@AutoConfigureAfter(CacheConfig.class)
public class WebConfig  implements ServletContextInitializer, EmbeddedServletContainerCustomizer{

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Inject
    private Environment env;


    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        //IE issue
        mappings.add("html", "text/html;charset=utf-8");
        //CloudFoundry issue
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
        EnumSet<DispatcherType> disps EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);

        logger.info("Web applcation fully configured ");
    }

    private void initCacheingHttpHeaderFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {

    }

    @Configuration
    public static class ErrorConfig implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
        }
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePageNotFound(ResourceNotFoundException ex) {

    }

}
