package com.billow.security.core.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 短信验证token
 *
 * @author liuyongtao
 * @create 2019-03-20 9:13
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;

    /**
     * 未认证成功之前的，传入手机号
     * <code>SmsAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     *
     * @param mobile 手机号
     */
    public SmsAuthenticationToken(Object mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    /**
     * 认证成功之后的，传入用户信息 <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal   用户信息
     * @param authorities 用户权限
     */
    public SmsAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}