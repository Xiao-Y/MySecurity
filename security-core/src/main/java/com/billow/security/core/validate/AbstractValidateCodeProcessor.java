package com.billow.security.core.validate;

import com.billow.security.core.exception.ValidateCodeException;
import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.support.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 抽象实现
 *
 * @author liuyongtao
 * @create 2019-03-19 14:59
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 系统收集所有的实现 CodeGenerator 接口的 Bean
     */
    @Autowired
    private Map<String, CodeGenerator> codeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    /**
     * 发送验证码
     *
     * @param [request, response, validateCode]
     * @return void
     * @author LiuYongTao
     * @date 2019/3/19 15:06
     */
    protected abstract void send(HttpServletRequest request, HttpServletResponse response, C validateCode) throws Exception;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        C validateCode = this.generate(request, response);
        System.out.println(validateCode.getClass().getName());
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
    protected void save(HttpServletRequest request, HttpServletResponse response, C validateCode) throws Exception {
        ValidateCodeType validateCodeType = this.getValidateCodeType();
        validateCodeRepository.save(request, response, validateCode, validateCodeType);
    }

    /**
     * 截取 ImageValidateCodeProcessor 和 SmsValidateCodeProcessor 的前缀
     *
     * @return com.billow.security.core.support.ValidateCodeType
     * @author LiuYongTao
     * @date 2019/3/20 16:15
     */
    public ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), ValidateCodeProcessor.class.getSimpleName());
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 生成校验码
     *
     * @param [request, response]
     * @return com.billow.security.core.support.ValidateCode
     * @author LiuYongTao
     * @date 2019/3/19 15:05
     */
    protected C generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidateCodeType validateCodeType = this.getValidateCodeType();
        String processorType = validateCodeType.toString().toLowerCase() + CodeGenerator.class.getSimpleName();
        // ImageCodeGenerator 和 SmsCodeGenerator
        CodeGenerator codeGenerator = codeGenerators.get(processorType);
        if (codeGenerator == null) {
            throw new ValidateCodeException("生成校验码处理器 " + processorType + "不存在");
        }
        ValidateCode generate = codeGenerator.generate(request, response);
        return (C) generate;
    }

    @Override
    public void validate(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        ValidateCodeType validateCodeType = this.getValidateCodeType();
        C codeInSession = (C) validateCodeRepository.get(request, response, validateCodeType);

        String codeInRequest = ServletRequestUtils.getStringParameter(request, validateCodeType.getParamNameOnValidate());

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("点击重新生成验证码");
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request, response, validateCodeType);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        // 扩展其它的校验
        this.validateExtend(request, response, codeInSession);
        validateCodeRepository.remove(request, response, validateCodeType);
    }

    /**
     * 扩展其它的校验
     *
     * @param request
     * @param response
     * @param codeInSession 放入session 中的数据
     * @return void
     * @author LiuYongTao
     * @date 2019/3/21 11:52
     */
    protected void validateExtend(HttpServletRequest request, HttpServletResponse response, C codeInSession) throws ValidateCodeException {

    }
}
