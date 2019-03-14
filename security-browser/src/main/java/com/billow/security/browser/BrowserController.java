package com.billow.security.browser;

import com.billow.security.core.utils.VerifyCodeUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuyongtao
 * @create 2019-03-14 10:39
 */
@RestController
public class BrowserController {

    private static final String SESSION_KEY_CODE = "SESSION_KEY_CODE";

    @GetMapping("/code/image")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int w = 120;
        int h = 30;
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_CODE, verifyCode);
    }
}
