package com.billow.security.core.properties;

/**
 * 图片验证码配置
 *
 * @author liuyongtao
 * @create 2019-03-15 11:20
 */
public class SmsCodeProperties {

    private int length = 4;
    private int expireIn = 30;
    private String urls;

    public int getLength() {
        return length;
    }

    public SmsCodeProperties setLength(int length) {
        this.length = length;
        return this;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public SmsCodeProperties setExpireIn(int expireIn) {
        this.expireIn = expireIn;
        return this;
    }

    public String getUrls() {
        return urls;
    }

    public SmsCodeProperties setUrls(String urls) {
        this.urls = urls;
        return this;
    }
}
