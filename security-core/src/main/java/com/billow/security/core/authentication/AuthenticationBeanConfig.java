package com.billow.security.core.authentication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author liuyongtao
 * @create 2019-03-13 14:39
 */
@Configuration
public class AuthenticationBeanConfig {

    @Bean
    @ConditionalOnMissingBean
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }
}
