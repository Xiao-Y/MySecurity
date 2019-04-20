package com.billow.security.core.social;

import com.billow.security.core.properties.QQProperties;
import com.billow.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    // 如果实现此接口，则会实现自动注册功能
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // 数据源，连接工厂，加密方式
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 设置 UserConnection 的前缀
        repository.setTablePrefix("security_");
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean
    public SpringSocialConfigurer supportSpringSocialConfigurer() {
        // 默认拦截的是url 前缀为 /auth 的 查看SocialAuthenticationFilter
//        return new SpringSocialConfigurer();

        SupportSocialSecurityConfig supportSocialSecurityConfig = new SupportSocialSecurityConfig();
        supportSocialSecurityConfig.setFilterProcessesUrl(securityProperties.getSocial().getFilterProcessesUrl());
        supportSocialSecurityConfig.setSignupUrl(securityProperties.getBrowser().getRegistPage());
        return supportSocialSecurityConfig;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }
}
