package com.billow.security.core.support;

import java.time.LocalDateTime;

/**
 * 短信验证扩展类
 *
 * @author liuyongtao
 * @create 2019-03-21 11:34
 */
public class SmsValidateCode extends ValidateCode {

    // 手机号
    private String mobile;

    public SmsValidateCode() {
    }

    public SmsValidateCode(String code, int expireIn) {
        super(code, expireIn);
    }

    public SmsValidateCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public String getMobile() {
        return mobile;
    }

    public SmsValidateCode setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
}
