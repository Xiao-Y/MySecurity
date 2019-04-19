package com.billow.security.core.social.wechat.connet;

import com.billow.security.core.social.wechat.api.WeChat;
import com.billow.security.core.social.wechat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author liuyongtao
 * @create 2019-03-22 9:58
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatServiceProvider(String appId, String appSecret) {
        // String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl
//        super(new OAuth2Template(appId, appSecret, authorizeUrl, accessTokenUrl));
        super(new WeChatOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public WeChat getApi(String accessToken) {
        return new WeChatImpl(accessToken);
    }
}
