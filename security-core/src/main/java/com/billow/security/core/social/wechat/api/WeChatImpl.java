package com.billow.security.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 微信在获取 accessToken 时，直接就返回了 openid
 *
 * @author liuyongtao
 * @date 2019/4/16 15:53
 */
public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    ObjectMapper objectMapper = new ObjectMapper();

    public WeChatImpl(String accessToken) {
        // 指定accessToken 从 url 路径上获取
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeChatUserInfo getUserInfo(String openId) {
        String url = String.format(URL_GET_USER_INFO, openId);
        String response = this.getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }

        WeChatUserInfo wechatUserInfo = null;
        try {
            wechatUserInfo = objectMapper.readValue(response, WeChatUserInfo.class);
        } catch (IOException e) {
            logger.error("获取微信用户信息异常。", e);
        }
        return wechatUserInfo;
    }

    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        // 默认字符集为 ISO-8859-1
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
