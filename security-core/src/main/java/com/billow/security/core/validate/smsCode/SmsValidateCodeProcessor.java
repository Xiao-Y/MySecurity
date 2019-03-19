package com.billow.security.core.validate.smsCode;

import com.billow.security.core.validate.AbstractValidateCodeProcessor;
import com.billow.security.core.validate.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码
 *
 * @author liuyongtao
 * @create 2019-03-19 16:00
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsCodeSender smsCodeGenerator;

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeGenerator.sender(mobile, validateCode.getCode());
    }
}
