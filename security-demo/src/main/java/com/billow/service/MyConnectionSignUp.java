package com.billow.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 用于自动完成注册。（当用户扫码登陆后，在用户表中未查询到数据，则自动生成用户表数据，自动完成注册）
 * <br/>
 * 流程：<br/>
 * 1.扫码登陆：拿到用户的QQ返回的信息<br/>
 * 2.通过 SocialAuthenticationProvider.authenticate 中的 toUserId 查询 UserConnection 表中是否已经存在数据<br/>
 * 1） 手动完成注册流程：<br/>
 * 1.1）.如果存在，则通过  userDetailsService.loadUserByUserId 查询出用户信息完成整个登陆过程，否则抛出 BadCredentialsException 异常<br/>
 * 1.2.如果抛出 BadCredentialsException 异常，则跳转到SupportSocialSecurityConfig.postProcess中 setSignupUrl 的页面完成注册或者绑定<br/>
 * 2.3.setSignupUrl 中跳转的方法中使用 providerSignInUtils.doPostSignUp 方法保存数据，完成 UserConnection 与用户表的关联<br/>
 * <p>
 * 2）自动完成注册流程：<br/>
 * 2.1）在 SocialAuthenticationProvider.authenticate 中的 toUserId 查询时，
 * usersConnectionRepository.findUserIdsWithConnection（以 JdbcUsersConnectionRepository.findUserIdsWithConnection 为例） 方法里面有一个 connectionSignUp<br/>
 * 2.2）如果实现了 connectionSignUp。执行 execute 方法时在用户表中插入用户数据,并且返回新用户id,则向 UserConnection 表中插入新数据<br/>
 * 2.3）通过  userDetailsService.loadUserByUserId 查询出用户信息完成整个注册、登陆过程
 * <p>
 *
 * @see com.billow.security.core.social.SupportSocialSecurityConfig
 * SupportSocialSecurityConfig.postProcess
 * @see org.springframework.social.security.SocialAuthenticationProvider
 * SocialAuthenticationProvider.authenticate
 * @see org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
 * JdbcUsersConnectionRepository.findUserIdsWithConnection
 */
@Component
public class MyConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        // 插入新用户数据,返回新用户的id
        return connection.getKey().getProviderUserId();
    }
}




















