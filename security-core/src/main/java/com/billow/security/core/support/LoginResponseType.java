package com.billow.security.core.support;

/**
 * 认证成功后的响应方式
 *
 * @author liuyongtao
 * @create 2019-03-13 14:52
 */
public enum LoginResponseType {
    /**
     * 跳转
     */
    REDIRECT,
    /**
     * 返回json
     */
    JSON
}
