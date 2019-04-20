package com.billow.security.browser;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.BaseResponse;
import com.billow.security.core.support.ResCodeEnum;
import com.billow.security.core.support.SecurityConstants;
import com.billow.security.core.support.SocialUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuyongtao
 * @create 2019-03-14 10:39
 */
@RestController
public class BrowserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    RequestCache requestCache = new HttpSessionRequestCache();
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 处理没有权限时的跳转
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的URL:{}", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLogInPage());
            }
        }
        BaseResponse baseResponse = new BaseResponse(ResCodeEnum.RESCODE_NOT_FOUND_SIGNIN_PAGE);
        return baseResponse;
    }

    /**
     * 获取社交用户数据
     *
     * @param request
     * @return
     */
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletRequestAttributes(request));
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setNickname(connection.getDisplayName());
        socialUserInfo.setHeadimg(connection.getImageUrl());
        return socialUserInfo;
    }

    /**
     * session 失效时处理
     *
     * @return
     */
    @GetMapping("/seesion/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseResponse sessionInvalid() {
        BaseResponse baseResponse = new BaseResponse(ResCodeEnum.RESCODE_SEESION_INVALID);
        return baseResponse;
    }
}
