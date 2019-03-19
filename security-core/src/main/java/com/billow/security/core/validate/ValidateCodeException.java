package com.billow.security.core.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author liuyongtao
 * @create 2019-03-15 10:12
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String message) {
        super(message);
    }
}
