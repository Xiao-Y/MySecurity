package com.billow.security.browser;

import com.billow.security.core.authentication.mobile.SmsAuthenticationSecurityConfig;
import com.billow.security.core.support.SecurityConstants;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    @Qualifier("rbacUsernameUserDetailsService")
    private UserDetailsService rbacUsernameUserDetailsService;
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginPage(SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL)
                .loginProcessingUrl(securityProperties.getBrowser().getLoginProcessingUrl())
                .successHandler(defaultAuthenticationSuccessHandler)
                .failureHandler(defaultAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(rbacUsernameUserDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(antMatchersPermitAll()).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.apply(smsAuthenticationSecurityConfig);
    }

    /**
     * 不需要认证的路径
     *
     * @return java.lang.String[]
     * @author LiuYongTao
     * @date 2019/3/15 16:03
     */
    private String[] antMatchersPermitAll() {
        List<String> list = new ArrayList<>();
        list.add(securityProperties.getBrowser().getSignInPage());
        list.add(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL);
        list.add("/code/image");
        list.add("/code/sms");
        return list.toArray(new String[list.size()]);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(rbacUsernameUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
