package com.billow.security.app.validate.code.impl;

import com.billow.security.core.support.ValidateCode;
import com.billow.security.core.support.ValidateCodeType;
import com.billow.security.core.validate.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(this.getKey(request, validateCodeType), validateCode, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(HttpServletRequest request, HttpServletResponse response, ValidateCodeType validateCodeType) {
        Object object = redisTemplate.opsForValue().get(this.getKey(request, validateCodeType));
        if (object == null) {
            return null;
        }
        return (ValidateCode) object;
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response, ValidateCodeType validateCodeType) {
        redisTemplate.delete(this.getKey(request, validateCodeType));
    }

    /**
     * 构建验证码放入 redis 时的 key
     *
     * @return
     */
    private String getKey(HttpServletRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new RuntimeException("请在请求头中添加 deviceId 参数");
        }
        return "CODE:" + validateCodeType.toString().toUpperCase() + ":" + deviceId;
    }
}