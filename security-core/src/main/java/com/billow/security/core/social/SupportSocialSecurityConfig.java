package com.billow.security.core.social;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 默认拦截 url 以/auth 开头的。以下为自定义
 */
public class SupportSocialSecurityConfig extends SpringSocialConfigurer {

    private String filterProcessesUrl;
    private String signupUrl;

//    public SupportSocialSecurityConfig(String filterProcessesUrl) {
//        this.filterProcessesUrl = filterProcessesUrl;
//    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        if (StringUtils.isNotBlank(filterProcessesUrl)) {
            socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);
        }
        if (StringUtils.isNotBlank(signupUrl)) {
            socialAuthenticationFilter.setSignupUrl(signupUrl);
        }
        return (T) socialAuthenticationFilter;
    }

    public SupportSocialSecurityConfig setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
        return this;
    }

    public SupportSocialSecurityConfig setSignupUrl(String signupUrl) {
        this.signupUrl = signupUrl;
        return this;
    }
}
