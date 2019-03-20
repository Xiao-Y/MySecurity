package com.billow.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证配置信息
 *
 * @author liuyongtao
 * @create 2019-03-20 11:17
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    @Qualifier("rbacMobileUserDetailsService")
    private UserDetailsService rbacMobileUserDetailsService;
    @Autowired
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsService(rbacMobileUserDetailsService);

        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
