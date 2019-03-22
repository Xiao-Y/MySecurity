package com.billow.security.core.validate.imageCode;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.SmsValidateCode;
import com.billow.security.core.utils.VerifyCodeUtils;
import com.billow.security.core.validate.AbstractValidateCodeProcessor;
import com.billow.security.core.support.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuyongtao
 * @create 2019-03-19 15:46
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) throws Exception {
        int w = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getBrowser().getValidate().getImageCode().getWidth());
        int h = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getBrowser().getValidate().getImageCode().getHeight());
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), validateCode.getCode());
    }
}
