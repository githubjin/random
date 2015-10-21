package random.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This filter is used in production to serve static resources generated by "grunt build"
 * Created by DaoSui on 2015/10/21.
 * it is configured to serve resources from the "dist' directory which is the Grunt
 * destination directory
 */
public class StaticResourcesProductionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Nothing to initialize
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String contextPath = httpRequest.getContextPath();
        String requestURI = httpRequest.getRequestURI();
        requestURI = StringUtils.substringAfter(requestURI, contextPath);
        if(StringUtils.equals("/", requestURI)) {
            requestURI = "/indec.html";
        }
        String newURI = "/dist" + requestURI;
        request.getRequestDispatcher(newURI).forward(request, response);
    }

    @Override
    public void destroy() {
//        Nothing to destrop
    }
}