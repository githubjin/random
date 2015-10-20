package random.configs.locale;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Angular cookie saved the locale with a double quote (%22en%22)
 * So the default CookieLocaleResolver#StringUtils.parseLocaleString(localePart)
 * is ont able to parse the locale
 *
 * This class will check if a double quote has bean added, if so it willl remove it
 * Created by Yo on 2015/10/20.
 */
public class AngularCookieLocaleResolver extends CookieLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        parseLocalCookieIfNecessary(request);
        return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
    }

    @Override
    public LocaleContext resolveLocaleContext(final HttpServletRequest request) {
        parseLocalCookieIfNecessary(request);
        return new TimeZoneAwareLocaleContext() {
            @Override
            public TimeZone getTimeZone() {
                return (TimeZone) request.getAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME);
            }

            @Override
            public Locale getLocale() {
                return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
            }
        };
    }

    @Override
    public void addCookie(HttpServletResponse response, String cookieValue) {
        //Mandatory cookie modification for angular to support the locale switching on the server side
        cookieValue = "%22" + cookieValue + "%22";
        super.addCookie(response, cookieValue);
    }

    private void parseLocalCookieIfNecessary(HttpServletRequest request) {
        if(request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME) == null) {
            Cookie cookie = WebUtils.getCookie(request, getCookieName());
            Locale locale = null;
            TimeZone timeZone = null;
            if(cookie != null) {
                String value = cookie.getValue();
                //Remove the double quote
                value = StringUtils.replace(value, "%22", "");
                String localePart = value;
                String timeZonePart = null;
                int spaceIndex = localePart.indexOf(' ');
                if(spaceIndex != -1) {
                    localePart = value.substring(0, spaceIndex);
                    timeZonePart = value.substring(spaceIndex + 1);
                }
                locale = (!"-".equals(localePart) ? StringUtils.parseLocaleString(localePart.replace('-', '_')) : null);
                if(timeZone != null) {
                    timeZone = StringUtils.parseTimeZoneString(timeZonePart);
                }
                if(logger.isTraceEnabled()) {
                    logger.trace("Parsed cookie value [" + cookie.getValue() + "] into locale '" + locale +
                            "'" + (timeZone != null ? " and time zone '" + timeZone.getID() + "'" : ""));
                }
            }
            request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, (locale != null ? locale: determineDefaultLocale(request)));
            request.setAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME, (timeZone != null ? timeZone : determineDefaultTimeZone(request)));
        }
    }
}
