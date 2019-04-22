package com.billow.security.browser.validate.code.impl;

import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.support.ValidateCodeType;
import com.billow.security.core.validate.ValidateCodeRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 浏览器 session 的保存方式
 *
 * @author liuyongtao
 * @create 2019-03-20 16:02
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    String SESSION_KEY_FOR_CODE_ = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(new ServletWebRequest(request), getSessionKey(validateCodeType), validateCode);
    }

    @Override
    public ValidateCode get(HttpServletRequest request, HttpServletResponse response, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(new ServletWebRequest(request), getSessionKey(validateCodeType));
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(new ServletWebRequest(request), getSessionKey(validateCodeType));
    }

    /**
     * 构建验证码放入session时的key
     *
     * @return SESSION_KEY_FOR_CODE_SMS 或者 SESSION_KEY_FOR_CODE_IMAGE
     */
    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_KEY_FOR_CODE_ + validateCodeType.toString().toUpperCase();
    }
}
