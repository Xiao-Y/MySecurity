package com.billow.security.core.properties;

/**
 * 社交登陆
 *
 * @author liuyongtao
 * @create 2019-03-22 10:49
 */
public class SocialProperties {

    QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public SocialProperties setQq(QQProperties qq) {
        this.qq = qq;
        return this;
    }
}
