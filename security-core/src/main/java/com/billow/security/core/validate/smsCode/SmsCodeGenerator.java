package com.billow.security.core.validate.smsCode;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.validate.CodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认的图片生成器
 *
 * @author liuyongtao
 * @create 2019-03-15 9:20
 */
public class SmsCodeGenerator implements CodeGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int expireIn = securityProperties.getBrowser().getValidate().getSmsCode().getExpireIn();
        int length = securityProperties.getBrowser().getValidate().getSmsCode().getLength();
        // 生成验证码
        String code = RandomStringUtils.randomNumeric(length);
        ValidateCode validateCode = new ValidateCode(code, expireIn);
        return validateCode;
    }

    public SmsCodeGenerator setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        return this;
    }
}
