package com.billow.security.core.social.wechat.connet;

import com.billow.security.core.social.wechat.api.Wechat;
import com.billow.security.core.social.wechat.api.WechatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author liuyongtao
 * @create 2019-03-22 9:58
 */
public class WechatServiceProvider extends AbstractOAuth2ServiceProvider<Wechat> {

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    public WechatServiceProvider(String appId, String appSecret) {
        // String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl
//        super(new OAuth2Template(appId, appSecret, authorizeUrl, accessTokenUrl));
        super(new WechatOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public Wechat getApi(String accessToken) {
        return new WechatImpl(appId, accessToken);
    }
}
