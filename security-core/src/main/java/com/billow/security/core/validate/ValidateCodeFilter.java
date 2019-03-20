package com.billow.security.core.validate;

import com.billow.security.core.exception.ValidateCodeException;
import com.billow.security.core.properties.SecurityProperties;
import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.support.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 短信验证码校验
 *
 * @author liuyongtao
 * @create 2019-03-15 9:58
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        this.addUrlToMap(securityProperties.getBrowser().getValidate().getImageCode().getUrls(), ValidateCodeType.IMAGE);
        this.addUrlToMap(securityProperties.getBrowser().getValidate().getSmsCode().getUrls(), ValidateCodeType.SMS);
    }

    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType validateCodeType = this.getValidateCodeType(request, response);
        if (validateCodeType != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + validateCodeType);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType).validate(request, response);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
            logger.info("验证码校验通过");
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request, HttpServletResponse response) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
