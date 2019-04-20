package com.billow.security.core.social.wechat.config;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.properties.WechatProperties;
import com.billow.security.core.social.view.SupportConnectView;
import com.billow.security.core.social.wechat.connet.WeChatOAuth2ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

@Configuration
// 当配置 support.security.social.qq.wechat-id 有值时，些配置才生效
@ConditionalOnProperty(prefix = "support.security.social.wechat", name = "app-id")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WechatProperties wechat = securityProperties.getSocial().getWechat();
        //
        return new WeChatOAuth2ConnectionFactory(wechat.getProviderId(), wechat.getAppId(), wechat.getAppSecret());
    }

    // 解绑、绑定者由这个视图处理
    @Bean({"connect/wechatConnect", "connect/wechatConnected"})
    @ConditionalOnMissingBean(name = "weChatConnectView")
    public View weChatConnectView() {
        return new SupportConnectView();
    }
}
