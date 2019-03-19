package com.billow.security.core.properties;

/**
 * 验证相关
 *
 * @author liuyongtao
 * @create 2019-03-15 11:23
 */
public class ValidateProperties {

    private ImageCodeProperties imageCode = new ImageCodeProperties();
    private SmsCodeProperties smsCode = new SmsCodeProperties();

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public ValidateProperties setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
        return this;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public ValidateProperties setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
        return this;
    }
}
