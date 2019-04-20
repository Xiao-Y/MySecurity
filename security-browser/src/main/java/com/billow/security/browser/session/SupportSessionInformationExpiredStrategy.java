package com.billow.security.browser.session;

import com.billow.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 并发登陆,超过最大登陆数据时顶掉前一个用户
 */
public class SupportSessionInformationExpiredStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public SupportSessionInformationExpiredStrategy(SecurityProperties securityPropertie) {
        super(securityPropertie);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        super.onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
