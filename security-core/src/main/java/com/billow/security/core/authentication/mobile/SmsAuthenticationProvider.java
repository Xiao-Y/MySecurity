package com.billow.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 短信验证处理器
 *
 * @author liuyongtao
 * @create 2019-03-20 9:27
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * 查询用户信息
     *
     * @param authentication 类型为 SmsAuthenticationFilter 中 attemptAuthentication 放入的 SmsAuthenticationToken
     * @return org.springframework.security.core.Authentication
     * @author LiuYongTao
     * @date 2019/3/20 9:32
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
        String mobile = (String) smsAuthenticationToken.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(smsAuthenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * 选择合适的token
     *
     * @param authentication AuthenticationToken 集合
     * @return boolean
     * @author LiuYongTao
     * @date 2019/3/20 9:31
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public SmsAuthenticationProvider setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }
}
