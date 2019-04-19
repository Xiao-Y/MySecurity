package com.billow.security.core.properties;

/**
 * wechat 的配置信息
 *
 * @author liuyongtao
 * @create 2019-03-22 10:48
 */
public class WechatProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;
    private String providerId = "wechat";


    public String getAppId() {
        return appId;
    }

    public WechatProperties setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public WechatProperties setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public String getProviderId() {
        return providerId;
    }

    public WechatProperties setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }
}
