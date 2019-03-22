package com.billow.security.core.social.qq.connet;

import com.billow.security.core.social.qq.api.QQ;
import com.billow.security.core.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author liuyongtao
 * @create 2019-03-22 9:58
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    public static final String authorizeUrl = "https://graph.qq.com/oauth2.0/authorize";
    public static final String accessTokenUrl = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    public QQServiceProvider(String appId, String secret) {
        // String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl
        super(new OAuth2Template(appId, secret, authorizeUrl, accessTokenUrl));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(appId, accessToken);
    }
}
