package com.billow.security.core.validate.smsCode.impl;

import com.billow.security.core.validate.smsCode.SmsCodeSender;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认实现发送验证码
 *
 * @author liuyongtao
 * @create 2019-03-19 14:30
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void sender(String mobile, String code) {

        if (StringUtils.isBlank(mobile)) {
            throw new RuntimeException("手机号不能为空！");
        }
        logger.info("手机号：{}，发送验证码：{}", mobile, code);
    }
}
