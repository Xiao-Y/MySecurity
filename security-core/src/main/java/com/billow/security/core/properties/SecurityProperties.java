package com.billow.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuyongtao
 * @create 2019-03-13 14:49
 */
@ConfigurationProperties("support.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public SecurityProperties setBrowser(BrowserProperties browser) {
        this.browser = browser;
        return this;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public SecurityProperties setSocial(SocialProperties social) {
        this.social = social;
        return this;
    }
}
