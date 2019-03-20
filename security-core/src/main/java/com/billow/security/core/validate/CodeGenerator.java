package com.billow.security.core.validate;

import com.billow.security.core.support.ValidateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CodeGenerator {

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
