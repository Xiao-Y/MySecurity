package com.billow.security.core.social.wechat.connet;

import com.billow.security.core.social.wechat.api.WeChat;
import com.billow.security.core.social.wechat.api.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 适配器，用于将不同服务提供商的个性化用户信息映射到 {@link org.springframework.social.connect.Connection}
 *
 * @author liuyongtao
 * @create 2019-03-22 10:12
 */
public class WeChatApiAdapter implements ApiAdapter<WeChat> {

    private String openId;

    public WeChatApiAdapter() {
    }

    public WeChatApiAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeChat api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChat api, ConnectionValues values) {
        WeChatUserInfo userInfo = api.getUserInfo(openId);
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
        // 主页url,QQ 没有
        values.setProfileUrl(null);
        // 用于关联用户信息
        values.setProviderUserId(userInfo.getOpenid());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat api) {
        return UserProfile.EMPTY;
    }

    @Override
    public void updateStatus(WeChat api, String message) {

    }
}
