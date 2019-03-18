package com.billow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component("passwordEncoder")
public class MyPasswordEncoder implements PasswordEncoder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String encode(CharSequence charSequence) {
        logger.info("encode:-->{}", charSequence);
        return "0000";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        logger.info("matches:-->rawPassword-->{}", rawPassword);
        logger.info("matches:-->encodedPassword-->{}", encodedPassword);
        return true;
    }
}
