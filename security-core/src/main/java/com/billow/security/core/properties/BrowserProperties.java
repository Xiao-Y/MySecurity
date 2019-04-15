package com.billow.security.core.properties;


import com.billow.security.core.support.LoginResponseType;
import com.billow.security.core.support.SecurityConstants;

/**
 * @author liuyongtao
 * @create 2019-03-13 14:50
 */
public class BrowserProperties {

    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    /**
     * 注册页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signUpPage = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;
    /**
     * 登录响应的方式，默认是json
     */
    private LoginResponseType signInResponseType = LoginResponseType.JSON;
    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
     * <p>
     * 只在signInResponseType为REDIRECT时生效
     */
    private String singInSuccessUrl;
    private String loginProcessingUrl = SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM;

    /**
     * 记住我过期时间（秒）
     */
    private int rememberMeSeconds = 60 * 60 * 24 * 30;

    private ValidateProperties validate = new ValidateProperties();

    public String getSignInPage() {
        return signInPage;
    }

    public BrowserProperties setSignInPage(String signInPage) {
        this.signInPage = signInPage;
        return this;
    }

    public LoginResponseType getSignInResponseType() {
        return signInResponseType;
    }

    public BrowserProperties setSignInResponseType(LoginResponseType signInResponseType) {
        this.signInResponseType = signInResponseType;
        return this;
    }

    public ValidateProperties getValidate() {
        return validate;
    }

    public BrowserProperties setValidate(ValidateProperties validate) {
        this.validate = validate;
        return this;
    }

    public String getSingInSuccessUrl() {
        return singInSuccessUrl;
    }

    public BrowserProperties setSingInSuccessUrl(String singInSuccessUrl) {
        this.singInSuccessUrl = singInSuccessUrl;
        return this;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public BrowserProperties setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
        return this;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public BrowserProperties setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
        return this;
    }

    public String getSignUpPage() {
        return signUpPage;
    }

    public BrowserProperties setSignUpPage(String signUpPage) {
        this.signUpPage = signUpPage;
        return this;
    }
}
