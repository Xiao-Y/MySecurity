package com.billow.security.core.validate;

import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.support.ValidateCodeType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验码处理方法
 *
 * @author LiuYongTao
 * @date 2019/3/20 15:57
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(HttpServletRequest request, HttpServletResponse response, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(HttpServletRequest request, HttpServletResponse response, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request
     * @param codeType
     */
    void remove(HttpServletRequest request, HttpServletResponse response, ValidateCodeType codeType);
}
