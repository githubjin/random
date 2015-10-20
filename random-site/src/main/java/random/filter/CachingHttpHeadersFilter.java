package random.filter;

import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by DaoSui on 2015/10/20.
 */
public class CachingHttpHeadersFilter implements Filter {

    //we consider the last modified date is the start up time of the server
    private final static long LAST_MODIFIED = System.currentTimeMillis();

    private long CACHE_TIME_TO_LIVE = TimeUnit.DAYS.toMillis(31l);

    private Environment env;

    public CachingHttpHeadersFilter(Environment env) {
        this.env = env;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        CACHE_TIME_TO_LIVE = TimeUnit.DAYS.toMillis(env.getProperty("randomX.http.cache.timeToLiveInDays", Long.class, 31L));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "max-age=" + CACHE_TIME_TO_LIVE + ", public");//http 1,1
        httpResponse.setHeader("Pragma", "cache");//http 1.0
        //setting expires header for proxy caching
        httpResponse.setDateHeader("Expires", CACHE_TIME_TO_LIVE + System.currentTimeMillis());
        httpResponse.setDateHeader("Last-Modified", LAST_MODIFIED);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //        Nothing to destroy
    }
}
