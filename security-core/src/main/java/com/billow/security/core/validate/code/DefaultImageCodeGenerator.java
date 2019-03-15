package com.billow.security.core.validate.code;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.utils.VerifyCodeUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 默认的图片生成器
 *
 * @author liuyongtao
 * @create 2019-03-15 9:20
 */
public class DefaultImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int expireIn = securityProperties.getBrowser().getValidate().getImageCode().getExpireIn();
        int length = securityProperties.getBrowser().getValidate().getImageCode().getLength();
        int w = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getBrowser().getValidate().getImageCode().getWidth());
        int h = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getBrowser().getValidate().getImageCode().getHeight());

        String verifyCode = VerifyCodeUtils.generateVerifyCode(length);
        ValidateCode validateCode = new ValidateCode(verifyCode, expireIn);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_CODE, validateCode);
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        return validateCode;
    }

    public DefaultImageCodeGenerator setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        return this;
    }
}
