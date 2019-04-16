package com.billow.security.core.social.qq.api;

import com.billow.security.core.social.qq.api.QQ;
import com.billow.security.core.social.qq.api.QQUserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author liuyongtao
 * @create 2019-03-22 9:39
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    Logger logger = LoggerFactory.getLogger(getClass());

    public final static String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    // 父类会自动携带accessToken
    public final static String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    // oauth_consumer_key
    private String appId;
    // openid
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String appId, String accessToken) {
        // 设置 access_token 到 url 路径上，默认是设置在 header
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        logger.info("QQImpl appId:{}, access_token:{}", appId, accessToken);

        // 获取用户的 openId
        String url = String.format(URL_GET_OPENID, accessToken);
        // callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String forObject = this.getRestTemplate().getForObject(url, String.class);
        logger.info("QQ callback info:{}", forObject);

        this.openId = StringUtils.substringBetween(forObject, "\"openid\":\"", "\"}");
        logger.info("QQ openId info:{}", this.openId);
        this.appId = appId;
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String forObject = this.getRestTemplate().getForObject(url, String.class);
        logger.info("QQ User Info:{}", forObject);
        try {
            QQUserInfo qqUserInfo = objectMapper.readValue(forObject, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息异常", e);
        }
    }
}
