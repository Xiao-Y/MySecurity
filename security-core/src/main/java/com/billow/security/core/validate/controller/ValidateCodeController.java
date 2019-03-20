package com.billow.security.core.validate.controller;

import com.billow.security.core.validate.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成校验码的请求处理器
 *
 * @author liuyongtao
 * @create 2019-03-14 10:24
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(request, response);
    }
}
