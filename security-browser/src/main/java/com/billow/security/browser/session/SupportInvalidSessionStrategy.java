package com.billow.security.browser.session;

import com.billow.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * session 失效处理
 */
public class SupportInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public SupportInvalidSessionStrategy(SecurityProperties securityPropertie) {
        super(securityPropertie);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.onSessionInvalid(request, response);
    }
}
