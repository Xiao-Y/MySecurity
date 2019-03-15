package com.billow.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ValidateCodeGenerator {

    public static final String SESSION_KEY_CODE = "SESSION_KEY_CODE";

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
