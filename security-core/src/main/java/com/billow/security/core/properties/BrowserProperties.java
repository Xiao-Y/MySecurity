package com.billow.security.core.properties;


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
     * 登录响应的方式，默认是json
     */
    private LoginResponseType signInResponseType = LoginResponseType.JSON;

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
}
