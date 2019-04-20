package com.billow.security.browser;

import com.billow.security.browser.logout.SupportLogoutSuccessHandler;
import com.billow.security.browser.session.SupportSessionInformationExpiredStrategy;
import com.billow.security.browser.session.SupportInvalidSessionStrategy;
import com.billow.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new SupportInvalidSessionStrategy(securityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new SupportSessionInformationExpiredStrategy(securityProperties);
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SupportLogoutSuccessHandler(securityProperties.getBrowser().getLogOutPage());
    }
}
