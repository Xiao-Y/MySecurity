package com.billow.security.app.authentication;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.BaseResponse;
import com.billow.security.core.support.LoginResponseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuyongtao
 * @create 2019-03-13 14:43
 */
@Component("defauleAuthenticationSuccessHandler")
public class DefauleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        logger.info("登录成功");

        // 参照 BasicAuthenticationFilter.doFilterInternal 方法
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求关头中无 ClientId 信息");
        }
        // 解析请求头中的 clientId 和 clientSecret
        String[] tokens = extractAndDecodeHeader(header, request);

        assert tokens.length == 2;

        String username = tokens[0];
        // 1、 获取 ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("ClientId 未查询到数据：" + username);
        }
        String password = tokens[1];
        if (!password.equals(clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("ClientSecret 不匹配：" + password);
        }
        // 2、创建 TokenRequest
        // custom 自定义类型
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientDetails.getClientId(),
                clientDetails.getScope(), "custom");
        // 3、 创建 OAuth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        // 4、创建 OAuth2Authentication
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        // 5、创建 AccessToken
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        // 6、页面返回 accessToken
        response.setContentType("application/json;charset=UTF-8");
        BaseResponse<OAuth2AccessToken> baseResponse = new BaseResponse();
        baseResponse.setResData(accessToken);
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }

    /**
     * 解析请求头中的 clientId 和 clientSecret
     *
     * @param header
     * @param request
     * @return clientId 和 clientSecret
     * @throws BadCredentialsException if the Basic header is not present or is not valid Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("无法解析身份认证令牌");
        }
        // clientId:clientSecret
        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("无效的身份认证令牌");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}
