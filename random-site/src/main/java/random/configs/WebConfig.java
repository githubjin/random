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
import random.filter.CachingHttpHeadersFilter;
import random.filter.StaticResourcesProductionFilter;
import random.filter.gzip.GZipServletFilter;
import random.support.ResourceNotFoundException;

import javax.inject.Inject;
import javax.servlet.*;
import java.util.*;

/**
 * Created by Yo on 2015/10/20.
 */
@Configuration
//@ControllerAdvice
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
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
        //  设置 cache headers
        this.initCacheingHttpHeaderFilter(servletContext, disps);
        //  设置 静态资源访问
        this.initStaticResoucesProductionFilter(servletContext, disps);
        //  对静态资源惊醒GZip压缩
        this.initGzipFilter(servletContext, disps);
        logger.info("Web applcation fully configured ");
    }

    /**
     * Initializes the GZip filter
     * @param servletContext
     * @param disps
     */
    private void initGzipFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        logger.debug("registering GZip filter");
        FilterRegistration.Dynamic gzipFilter = servletContext.addFilter("gzipFilter", new GZipServletFilter());
        Map<String, String> parameters = new HashMap<String, String>();
        gzipFilter.setInitParameters(parameters);
        gzipFilter.addMappingForUrlPatterns(disps, true, "*.css");
        gzipFilter.addMappingForUrlPatterns(disps, true, "*.json");
        gzipFilter.addMappingForUrlPatterns(disps, true, "*.html");
        gzipFilter.addMappingForServletNames(disps, true, "*.js");
        gzipFilter.addMappingForServletNames(disps, true, "*.svg");
        gzipFilter.addMappingForServletNames(disps, true, "*.ttf");
        gzipFilter.addMappingForUrlPatterns(disps, true, "/api/*");
        gzipFilter.addMappingForUrlPatterns(disps, true, "/metrics/*");
        gzipFilter.setAsyncSupported(true);
    }

    /**
     * Initializes the static resources produnction filter
     * @param servletContext
     * @param disps
     */
    private void initStaticResoucesProductionFilter(ServletContext servletContext,
                                                    EnumSet<DispatcherType> disps) {
        logger.debug("Registering static resources production Filter");
        FilterRegistration.Dynamic staticResourcesProductionFilter = servletContext.addFilter("staticResourcesProductionFilter", new StaticResourcesProductionFilter());

        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/index.html");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
        staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
        staticResourcesProductionFilter.setAsyncSupported(true);
    }

    /**
     * Initializes the caching HTTP Headers Filter
     * @param servletContext
     * @param disps
     */
    private void initCacheingHttpHeaderFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        logger.debug("registering caching http headers filter");
        FilterRegistration.Dynamic cachingHttpHeadersPilter = servletContext.addFilter("cachingHttpHeadersPilter", new CachingHttpHeadersFilter(env));

        cachingHttpHeadersPilter.addMappingForUrlPatterns(disps, true, "/assets/*");
        cachingHttpHeadersPilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
        cachingHttpHeadersPilter.setAsyncSupported(true);
    }

    @Configuration
    public static class ErrorConfig implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
        }
    }

/*    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePageNotFound(ResourceNotFoundException ex) {

    }*/

}
