package com.billow.security.core.validate.smsCode;

/**
 * 发送这短信验证码
 *
 * @author LiuYongTao
 * @date 2019/3/19 14:29
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return void
     * @author LiuYongTao
     * @date 2019/3/19 14:30
     */
    void sender(String mobile, String code);
}
