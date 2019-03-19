package com.billow.security.core.validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证操作流程
 *
 * @author LiuYongTao
 * @date 2019/3/19 14:53
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码生成的前缀
     *
     * @author LiuYongTao
     * @date 2019/3/19 14:57
     */
    String SESSION_KEY_FOR_CODE_ = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码流程
     *
     * @param [request, response]
     * @return com.billow.security.core.validate.ValidateCode
     * @author LiuYongTao
     * @date 2019/3/19 14:55
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
