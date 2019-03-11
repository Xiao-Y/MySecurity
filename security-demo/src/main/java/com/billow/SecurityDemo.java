package com.billow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liuyongtao
 * @create 2019-03-11 11:22
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class SecurityDemo {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
