package com.billow.security.core.properties;

/**
 * 社交登陆
 *
 * @author liuyongtao
 * @create 2019-03-22 10:49
 */
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    QQProperties qq = new QQProperties();
    WechatProperties wechat = new WechatProperties();

    public QQProperties getQq() {
        return qq;
    }

    public SocialProperties setQq(QQProperties qq) {
        this.qq = qq;
        return this;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public SocialProperties setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
        return this;
    }

    public WechatProperties getWechat() {
        return wechat;
    }

    public SocialProperties setWechat(WechatProperties wechat) {
        this.wechat = wechat;
        return this;
    }
}
