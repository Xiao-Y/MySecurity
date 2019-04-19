package com.billow.security.core.social.wechat.connet;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author liuyongtao
 * @create 2019-04-16 16:28
 */
public class WeChatAccessGrant extends AccessGrant {

    private String openId;

    public WeChatAccessGrant() {
        super("");
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

}