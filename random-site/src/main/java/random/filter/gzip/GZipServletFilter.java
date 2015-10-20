package random.filter.gzip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DaoSui on 2015/10/21.
 */
public class GZipServletFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(GZipServletFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //noting to initialize
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if(!isIncluded(httpRequest) && acceptsGZipEncoding(httpRequest) && !response.isCommitted()) {
           //client accepts ziiped centent
            if(log.isDebugEnabled()) {
                log.trace("{} Written with gzip compression", httpRequest.getRequestURL());
            }
            //create a gzip stream
        }
    }

    /**
     * Chacks is the request uri is an include These cannot be gzipped
     * @param request
     * @return
     */
    private boolean isIncluded(final HttpServletRequest request) {
        //get uri through jsp include ; <jsp:include="123123.jsp">
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        boolean includeRequest = !(uri == null);
        if(includeRequest && log.isDebugEnabled()) {
            log.debug("{} resulted in an include request. This is unusable, because"
                            + "the response will be assembled into the overrall response. Not gzipping.",
                    request.getRequestURL());
        }
        return includeRequest;
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpServletRequest) {
        String acceptEncoding = httpServletRequest.getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.equals("gzip");
    }


    @Override
    public void destroy() {
//        noting to destroy
    }
}
