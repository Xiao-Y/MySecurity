package com.billow.security.core.validate.smsCode;

import com.billow.security.core.exception.ValidateCodeException;
import com.billow.security.core.support.SmsValidateCode;
import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.validate.AbstractValidateCodeProcessor;
import org.apache.commons.lang3.StringUtils;
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
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeGenerator;

    @Override
    protected void send(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeGenerator.sender(mobile, validateCode.getCode());
    }

//    @Override
//    protected void save(HttpServletRequest request, HttpServletResponse response, SmsValidateCode validateCode) throws Exception {
//        String mobileInParameter = request.getParameter("mobile");
//        validateCode.setMobile(mobileInParameter);
//        super.save(request, response, validateCode);
//    }
//
//    @Override
//    protected void validateExtend(HttpServletRequest request, HttpServletResponse response, SmsValidateCode codeInSession) throws ValidateCodeException {
//        String mobileInParameter = request.getParameter("mobile");
//        if (StringUtils.isBlank(mobileInParameter)) {
//            throw new ValidateCodeException("手机号不能为空");
//        }
//        if (mobileInParameter.equals(codeInSession.getMobile())) {
//            throw new ValidateCodeException("输入的验证码与手机号不对应");
//        }
//    }
}
