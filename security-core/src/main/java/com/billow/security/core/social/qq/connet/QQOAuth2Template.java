package com.billow.security.core.social.qq.connet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OAuth2Template 不支持 text/html
 */
public class QQOAuth2Template extends OAuth2Template {

    Logger logger = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);

        // OAuth2Template.exchangeForAccess 调用时，只有 useParametersForClientAuthentication 为true 时，才会 set client_id 和 client_secret
        super.setUseParametersForClientAuthentication(true);
    }

    /**
     * 添加一个可以处理 text/html 的转换器,因数qq 返回的是xxxx&xxxx&xxx 的字符串
     *
     * @return
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        // 发送请求,返回的是 access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        logger.info("postForAccessGrant return :{}", responseStr);
        Map<String, Object> result = new HashMap<>();
        String[] item = responseStr.split("&");

        String accessToken = StringUtils.substringAfterLast(item[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(item[1], "="));
        String refreshToken = StringUtils.substringAfterLast(item[2], "=");

        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }
}
