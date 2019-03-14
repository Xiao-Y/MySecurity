package com.billow.security.browser.authentication;

import com.billow.security.core.properties.LoginResponseType;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.BaseResponse;
import com.billow.security.core.support.ResCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuyongtao
 * @create 2019-03-13 15:41
 */
@Component("defaultAuthenticationFailureHandler")
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            BaseResponse<String> baseResponse = new BaseResponse(ResCodeEnum.RESCODE_NOT_FOUND_USER.getStatusCode());
            baseResponse.setResData(exception.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
        } else {
            // 如果设置了imooc.security.browser.singInSuccessUrl，总是跳到设置的地址上
            // 如果没设置，则尝试跳转到登录之前访问的地址上，如果登录前访问地址为空，则跳到网站根路径上
//            if (StringUtils.isNotBlank(securityProperties.getBrowser().getSingInSuccessUrl())) {
//                requestCache.removeRequest(request, response);
//                setAlwaysUseDefaultTargetUrl(true);
//                setDefaultTargetUrl(securityProperties.getBrowser().getSingInSuccessUrl());
//            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
