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
    private String logInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE;
    /**
     * 登录时，处理登陆信息的url
     */
    private String loginUrl = SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM;
    /**
     * 注册页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String registPage = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;
    /**
     * 点击退出时的url
     */
    private String logOutUrl = SecurityConstants.DEFAULT_SIGN_OUT_URL;
    /**
     * 退出成功时,跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String logOutPage = SecurityConstants.DEFAULT_SIGN_OUT_PAGE;
    /**
     * 登录响应的方式，默认是json
     */
    private LoginResponseType logInResponseType = LoginResponseType.JSON;
    /**
     * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
     * <p>
     * 只在logInResponseType为REDIRECT时生效
     */
    private String logInSuccessUrl;


    /**
     * 记住我过期时间（秒）
     */
    private int rememberMeSeconds = 60 * 60 * 24 * 30;

    private ValidateProperties validate = new ValidateProperties();

    private SessionProperties session = new SessionProperties();

    public String getLogInPage() {
        return logInPage;
    }

    public BrowserProperties setLogInPage(String logInPage) {
        this.logInPage = logInPage;
        return this;
    }


    public ValidateProperties getValidate() {
        return validate;
    }

    public BrowserProperties setValidate(ValidateProperties validate) {
        this.validate = validate;
        return this;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public BrowserProperties setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
        return this;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public BrowserProperties setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
        return this;
    }

    public String getRegistPage() {
        return registPage;
    }

    public BrowserProperties setRegistPage(String registPage) {
        this.registPage = registPage;
        return this;
    }

    public SessionProperties getSession() {
        return session;
    }

    public BrowserProperties setSession(SessionProperties session) {
        this.session = session;
        return this;
    }

    public String getLogOutUrl() {
        return logOutUrl;
    }

    public BrowserProperties setLogOutUrl(String logOutUrl) {
        this.logOutUrl = logOutUrl;
        return this;
    }

    public String getLogOutPage() {
        return logOutPage;
    }

    public BrowserProperties setLogOutPage(String logOutPage) {
        this.logOutPage = logOutPage;
        return this;
    }

    public LoginResponseType getLogInResponseType() {
        return logInResponseType;
    }

    public BrowserProperties setLogInResponseType(LoginResponseType logInResponseType) {
        this.logInResponseType = logInResponseType;
        return this;
    }

    public String getLogInSuccessUrl() {
        return logInSuccessUrl;
    }

    public BrowserProperties setLogInSuccessUrl(String logInSuccessUrl) {
        this.logInSuccessUrl = logInSuccessUrl;
        return this;
    }
}
