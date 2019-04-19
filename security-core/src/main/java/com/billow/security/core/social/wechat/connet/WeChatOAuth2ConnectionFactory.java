package com.billow.security.core.social.wechat.connet;

import com.billow.security.core.social.wechat.api.WeChat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * 微信连接工厂
 *
 * @author liuyongtao
 * @create 2019-03-22 10:15
 */
public class WeChatOAuth2ConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId 服务商id；自定义字符串；也是后面添加social的过滤，
     *                   过滤器帮我们拦截的url其中的某一段地址 /auth/qq
     * @param appId      应用的id
     * @param appSecret  应用的秘钥
     */
    public WeChatOAuth2ConnectionFactory(String providerId, String appId, String appSecret) {
        /**
         * serviceProvider 用于执行授权流和获取本机服务API实例的ServiceProvider模型
         * apiAdapter      适配器，用于将不同服务提供商的个性化用户信息映射到 {@link Connection}
         */
        super(providerId, new WeChatServiceProvider(appId, appSecret), new WeChatApiAdapter());
    }

    /**
     * 由于微信的 openId 是和 accessToken 一起返回的，所以在这里直接根据 accessToken 设置 providerUserId 即可，不用像QQ那样通过 QQAdapter 来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeChatAccessGrant) {
            return ((WeChatAccessGrant) accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<WeChat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(getProviderId(), this.extractProviderUserId(accessGrant),
                accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(),
                this.getOAuth2ServiceProvider(), getApiAdapter(this.extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeChat> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, this.getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    /**
     * 多实例 WechatApiAdapter，通过 openId 获取不能的用户信息
     *
     * @param openId ProviderUserId
     * @return org.springframework.social.connect.ApiAdapter<com.billow.security.core.social.wechat.api.Wechat>
     * @author LiuYongTao
     * @date 2019/4/19 11:50
     */
    private ApiAdapter<WeChat> getApiAdapter(String openId) {
        return new WeChatApiAdapter(openId);
    }

    private OAuth2ServiceProvider<WeChat> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeChat>) getServiceProvider();
    }
}
