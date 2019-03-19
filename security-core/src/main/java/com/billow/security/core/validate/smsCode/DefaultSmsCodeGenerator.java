package com.billow.security.core.validate.smsCode;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.validate.ValidateCode;
import com.billow.security.core.validate.CodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认的图片生成器
 *
 * @author liuyongtao
 * @create 2019-03-15 9:20
 */
public class DefaultSmsCodeGenerator implements CodeGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int expireIn = securityProperties.getBrowser().getValidate().getSmsCode().getExpireIn();
        int length = securityProperties.getBrowser().getValidate().getSmsCode().getLength();
        // 生成验证码
        String code = RandomStringUtils.randomNumeric(length);
        ValidateCode validateCode = new ValidateCode(code, expireIn);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_CODE_SMS, validateCode);
        return validateCode;
    }

    public DefaultSmsCodeGenerator setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        return this;
    }
}
