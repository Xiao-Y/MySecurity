package com.billow.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 生成校验码的请求处理器
 *
 * @author liuyongtao
 * @create 2019-03-14 10:24
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeGenerator defaultImageCodeGenerator;

    @GetMapping("/code/image")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        defaultImageCodeGenerator.generate(request, response);
    }
}
