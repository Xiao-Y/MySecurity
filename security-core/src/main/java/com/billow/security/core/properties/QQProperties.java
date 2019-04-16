package com.billow.security.core.properties;

/**
 * QQ 的配置信息
 *
 * @author liuyongtao
 * @create 2019-03-22 10:48
 */
public class QQProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;
    private String providerId = "qq";


    public String getAppId() {
        return appId;
    }

    public QQProperties setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public QQProperties setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public String getProviderId() {
        return providerId;
    }

    public QQProperties setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }
}
