package com.billow.security.browser.logout;

import com.billow.security.core.support.BaseResponse;
import com.billow.security.core.support.ResCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SupportLogoutSuccessHandler implements LogoutSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String signOutSuccessUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public SupportLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");

        if (StringUtils.isBlank(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new BaseResponse<>(ResCodeEnum.RESCODE_LOGOUT_SUCCESS)));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }

    }
}
