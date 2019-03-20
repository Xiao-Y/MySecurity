package com.billow.security.core.validate;

import com.billow.security.core.exception.ValidateCodeException;
import com.billow.security.core.support.ValidateCodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 校验码处理器的查询器
 *
 * @author liuyongtao
 * @create 2019-03-20 15:28
 */
@Component
public class ValidateCodeProcessorHolder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    /**
     * 查询校验码处理器
     *
     * @param type 校验码处理器类型
     * @return com.billow.security.core.validate.ValidateCodeProcessor
     * @author LiuYongTao
     * @date 2019/3/20 15:32
     */
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String key = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        logger.info("校验码处理器：{}", key);
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(key);
        if (validateCodeProcessor == null) {
            throw new ValidateCodeException("没有找到 " + key + " 校验码处理器");
        }
        return validateCodeProcessor;
    }

    /**
     * 查询校验码处理器
     *
     * @param validateCodeType 校验码处理器类型
     * @return com.billow.security.core.validate.ValidateCodeProcessor
     * @author LiuYongTao
     * @date 2019/3/20 15:32
     */
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType validateCodeType) {
        return this.findValidateCodeProcessor(validateCodeType.toString().toLowerCase());
    }
}
