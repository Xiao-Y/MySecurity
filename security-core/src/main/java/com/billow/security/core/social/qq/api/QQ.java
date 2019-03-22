package com.billow.security.core.social.qq.api;

import com.billow.security.core.social.qq.QQUserInfo;

public interface QQ {

    /**
     * 获取qq用户信息
     *
     * @return com.billow.security.core.social.qq.QQUserInfo
     * @author LiuYongTao
     * @date 2019/3/22 9:55
     */
    QQUserInfo getUserInfo();
}
