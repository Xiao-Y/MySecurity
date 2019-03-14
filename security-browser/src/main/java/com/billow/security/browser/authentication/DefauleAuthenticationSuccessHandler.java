package com.billow.security.browser.authentication;

import com.billow.security.core.properties.LoginResponseType;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuyongtao
 * @create 2019-03-13 14:43
 */
@Component("defauleAuthenticationSuccessHandler")
public class DefauleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        logger.info("登录成功");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
            response.setContentType("application/json;charset=UTF-8");
            String type = authentication.getClass().getName();
            BaseResponse<String> baseResponse = new BaseResponse();
            baseResponse.setResData(type);
            response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
        }else{
            // 如果设置了imooc.security.browser.singInSuccessUrl，总是跳到设置的地址上
            // 如果没设置，则尝试跳转到登录之前访问的地址上，如果登录前访问地址为空，则跳到网站根路径上
//            if (StringUtils.isNotBlank(securityProperties.getBrowser().getSingInSuccessUrl())) {
//                requestCache.removeRequest(request, response);
//                setAlwaysUseDefaultTargetUrl(true);
//                setDefaultTargetUrl(securityProperties.getBrowser().getSingInSuccessUrl());
//            }
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
