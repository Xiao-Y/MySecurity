package com.billow.security.core.validate.config;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.validate.CodeGenerator;
import com.billow.security.core.validate.imageCode.ImageCodeGenerator;
import com.billow.security.core.validate.smsCode.SmsCodeGenerator;
import com.billow.security.core.validate.smsCode.impl.DefaultSmsCodeSender;
import com.billow.security.core.validate.smsCode.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyongtao
 * @create 2019-03-15 9:46
 */
@Configuration
public class ValidateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public CodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public CodeGenerator smsCodeGenerator() {
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeSender() {
        DefaultSmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
        return smsCodeSender;
    }
}
