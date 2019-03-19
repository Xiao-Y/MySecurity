package com.billow.security.core.validate.smsCode;

import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.validate.ValidateCode;
import com.billow.security.core.validate.ValidateCodeException;
import com.billow.security.core.validate.CodeGenerator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 短信验证码校验
 *
 * @author liuyongtao
 * @create 2019-03-15 9:58
 */
public class ValidateSmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final static Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getBrowser().getValidate().getImageCode().getUrls(), ",");
        if (ArrayUtils.isNotEmpty(configUrls)) {
            for (String configUrl : configUrls) {
                urls.add(configUrl);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                try {
                    validate(request);
                } catch (ValidateCodeException e) {
                    authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验图片验证码是否正确
     *
     * @param [request]
     * @return void
     * @author LiuYongTao
     * @date 2019/3/15 16:35
     */
    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);

        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, CodeGenerator.SESSION_KEY_CODE_IMAGE);
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "mobile");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空！");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(servletWebRequest, CodeGenerator.SESSION_KEY_CODE_SMS);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, CodeGenerator.SESSION_KEY_CODE_SMS);
    }

    public ValidateSmsCodeFilter setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        return this;
    }

    public ValidateSmsCodeFilter setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        return this;
    }
}
