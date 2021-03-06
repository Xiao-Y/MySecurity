package com.billow.security.core.social.qq.config;

import com.billow.security.core.properties.QQProperties;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.social.qq.connet.QQOAuth2ConnectionFactory;
import com.billow.security.core.social.view.SupportConnectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

@Configuration
// 当配置 support.security.social.qq.app-id 有值时，些配置才生效
@ConditionalOnProperty(prefix = "support.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        //
        return new QQOAuth2ConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }

    // 解绑、绑定者由这个视图处理
    @Bean({"connect/qqConnect", "connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqChatConnectView")
    public View qqChatConnectView() {
        return new SupportConnectView();
    }
}
