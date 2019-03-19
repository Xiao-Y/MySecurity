package com.billow.security.core.validate;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.validate.imageCode.DefaultImageCodeGenerator;
import com.billow.security.core.validate.smsCode.DefaultSmsCodeGenerator;
import com.billow.security.core.validate.smsCode.DefaultSmsCodeSender;
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
        DefaultImageCodeGenerator imageCodeGenerator = new DefaultImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public CodeGenerator smsCodeGenerator() {
        DefaultSmsCodeGenerator smsCodeGenerator = new DefaultSmsCodeGenerator();
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
