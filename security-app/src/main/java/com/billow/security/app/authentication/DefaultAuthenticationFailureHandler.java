package com.billow.security.app.authentication;

import com.billow.security.core.exception.ValidateCodeException;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.BaseResponse;
import com.billow.security.core.support.LoginResponseType;
import com.billow.security.core.support.ResCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyongtao
 * @create 2019-03-13 15:41
 */
@Component("defaultAuthenticationFailureHandler")
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        String resCode = ResCodeEnum.RESCODE_OTHER_ERROR.getStatusCode();

        if (exception instanceof ValidateCodeException) {
            resCode = ResCodeEnum.RESCODE_NOT_FOUND_VALIDATE.getStatusCode();
        } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            resCode = ResCodeEnum.RESCODE_NOT_FOUND_USER.getStatusCode();
        }
        logger.info("登录失败:{}", ResCodeEnum.getResCodeEnum(resCode));

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLogInResponseType())) {
            response.setContentType("application/json;charset=UTF-8");

            BaseResponse<String> baseResponse = new BaseResponse(resCode);
            baseResponse.setResData(exception.getMessage());
            Map<String, String> map = new HashMap<>();
            map.put("ExceptionType", exception.getClass().getName());
            baseResponse.setExt(map);
            response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
