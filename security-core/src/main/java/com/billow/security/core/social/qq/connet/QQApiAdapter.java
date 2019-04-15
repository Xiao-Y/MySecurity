package com.billow.security.core.social.qq.connet;

import com.billow.security.core.social.qq.api.QQUserInfo;
import com.billow.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 适配器，用于将不同服务提供商的个性化用户信息映射到 {@link org.springframework.social.connect.Connection}
 *
 * @author liuyongtao
 * @create 2019-03-22 10:12
 */
public class QQApiAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 主页url,QQ 没有
        values.setProfileUrl(null);
        // 用于关联用户信息
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return UserProfile.EMPTY;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
