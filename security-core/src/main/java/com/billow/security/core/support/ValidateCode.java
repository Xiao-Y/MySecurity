package com.billow.security.core.support;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码信息封装类
 *
 * @author liuyongtao
 * @create 2019-03-15 9:18
 */
public class ValidateCode implements Serializable {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode() {
    }

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public ValidateCode setCode(String code) {
        this.code = code;
        return this;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public ValidateCode setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
        return this;
    }
}
