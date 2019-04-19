package com.billow.security.core.social.wechat.api;

/**
 * 获取用户信息
 *
 * @author liuyongtao
 * @create 2019-04-16 15:47
 */
public interface WeChat {

    /**
     * 获取微信用户信息
     *
     * @param [openId]
     * @return com.billow.security.core.social.wechat.api.WechatUserInfo
     * @author LiuYongTao
     * @date 2019/4/16 15:49
     */
    WeChatUserInfo getUserInfo(String openId);
}
