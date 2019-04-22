package com.billow.security.app;

import com.billow.security.core.authentication.mobile.SmsAuthenticationSecurityConfig;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.properties.SocialProperties;
import com.billow.security.core.support.SecurityConstants;
import com.billow.security.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class SupportResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Autowired
    private SpringSocialConfigurer supportSpringSocialConfigurer;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                // 登陆页面
                .loginPage(securityProperties.getBrowser().getLogInPage())
                // 登陆处理
                .loginProcessingUrl(securityProperties.getBrowser().getLoginUrl())
                // 登陆成功处理
                .successHandler(defaultAuthenticationSuccessHandler)
                // 登陆失败处理
                .failureHandler(defaultAuthenticationFailureHandler)
                .and()
                // 权限管理
                .authorizeRequests()
                .antMatchers(antMatchersPermitAll()).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        // 验证码
        http.apply(smsAuthenticationSecurityConfig);
        // 社交登陆
        http.apply(supportSpringSocialConfigurer);
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
        list.add(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL);
        list.add(securityProperties.getBrowser().getLogInPage());
        list.add(securityProperties.getBrowser().getRegistPage());
        list.add("/code/image");
        list.add("/code/sms");
        list.add("/seesion/invalid");
        list.add(securityProperties.getBrowser().getLogOutPage());
        // /auth/qq
        SocialProperties social = securityProperties.getSocial();
        list.add(social.getFilterProcessesUrl() + "/" + social.getQq().getProviderId());
        list.add(social.getFilterProcessesUrl() + "/" + social.getWechat().getProviderId());
        // TODO
        list.add("/user/regist");
        return list.toArray(new String[list.size()]);
    }
}
