package com.billow.security.core.validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CodeGenerator {

    String SESSION_KEY_CODE_SMS = "SESSION_KEY_CODE_SMS";
    String SESSION_KEY_CODE_IMAGE = "SESSION_KEY_CODE_IMAGE";

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
