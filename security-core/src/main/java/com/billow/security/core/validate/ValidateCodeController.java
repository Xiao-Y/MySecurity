package com.billow.security.core.validate;

import com.billow.security.core.validate.smsCode.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 生成校验码的请求处理器
 *
 * @author liuyongtao
 * @create 2019-03-14 10:24
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type + "ValidateCodeProcessor").create(request, response);
    }
//
//    @GetMapping("/code/sms")
//    public void getCodeSms(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//
//        ValidateCode validateCode = defaultSmsCodeGenerator.generate(request, response);
//        defaultSmsCodeSender.sender(mobile, validateCode.getCode());
//    }
}
