package com.billow.security.core.validate.imageCode;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.utils.VerifyCodeUtils;
import com.billow.security.core.validate.CodeGenerator;
import com.billow.security.core.support.ValidateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认的图片生成器
 *
 * @author liuyongtao
 * @create 2019-03-15 9:20
 */
public class ImageCodeGenerator implements CodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int expireIn = securityProperties.getBrowser().getValidate().getImageCode().getExpireIn();
        int length = securityProperties.getBrowser().getValidate().getImageCode().getLength();

        String verifyCode = VerifyCodeUtils.generateVerifyCode(length);
        ValidateCode validateCode = new ValidateCode(verifyCode, expireIn);

        return validateCode;
    }

    public ImageCodeGenerator setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        return this;
    }
}
