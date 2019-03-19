package com.billow.security.core.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 抽象实现
 *
 * @author liuyongtao
 * @create 2019-03-19 14:59
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 系统收集所有的实现 CodeGenerator 接口的 Bean
     */
    @Autowired
    private Map<String, CodeGenerator> codeGenerators;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidateCode validateCode = this.generate(request, response);
        this.save(request, response, validateCode);
        this.send(request, response, validateCode);
    }

    /**
     * 保存验证码到session中
     *
     * @param [request, response, validateCode]
     * @return void
     * @author LiuYongTao
     * @date 2019/3/19 15:05
     */
    private void save(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) throws Exception {
        String key = ValidateCodeProcessor.SESSION_KEY_FOR_CODE_ + getProcessorType(request, response).toUpperCase();
        sessionStrategy.setAttribute(new ServletWebRequest(request), key, validateCode.getCode());
    }

    /**
     * 根据请求的uri 获取校验码类型
     *
     * @param [request, response]
     * @return java.lang.String
     * @author LiuYongTao
     * @date 2019/3/19 15:30
     */
    private String getProcessorType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return StringUtils.substringAfter(request.getRequestURI(), "/code/") + "CodeGenerator";
    }

    /**
     * 生成校验码
     *
     * @param [request, response]
     * @return com.billow.security.core.validate.ValidateCode
     * @author LiuYongTao
     * @date 2019/3/19 15:05
     */
    private ValidateCode generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String processorType = this.getProcessorType(request, response);
        return codeGenerators.get(processorType).generate(request, response);
    }

    /**
     * 发送验证码
     *
     * @param [request, response, validateCode]
     * @return void
     * @author LiuYongTao
     * @date 2019/3/19 15:06
     */
    protected abstract void send(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) throws Exception;

}
