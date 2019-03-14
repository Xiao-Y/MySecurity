package com.billow.security.browser;

import com.billow.security.core.properties.SecurityConstants;
import com.billow.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author liuyongtao
 * @create 2019-03-12 9:55
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String signInPage = securityProperties.getBrowser().getSignInPage();

        http.formLogin()
                .loginPage(signInPage)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(defaultAuthenticationSuccessHandler)
                .failureHandler(defaultAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(signInPage, "/code/image").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
