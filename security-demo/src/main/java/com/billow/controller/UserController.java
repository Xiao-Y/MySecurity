package com.billow.controller;

import com.billow.dto.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuyongtao
 * @create 2019-03-11 13:51
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 扫码登陆时，如果没有注册过的，跳到此方法
     *
     * @param user
     * @param request
     */
    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {
        log.info("注册用户：{}", user);
        // 不管是注册用户还是绑定用户，都会拿到用户唯一标志。
        // 如果是注册，保存用户信息到用户表，返回生成的id，否则通过用户名查询出用户信息，放入原始的id
        // doPostSignUp 第一个参数放入的值，最终会放到表的 userId 中，
        // 在使用 SocialUserDetailsService 查询时，也要用对应的字段查询
        providerSignInUtils.doPostSignUp(user.getId(), new ServletRequestAttributes(request));

    }

    @GetMapping("/me")
    public Object getCurrentUser(Authentication user, HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {

//		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
//
//		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
//					.parseClaimsJws(token).getBody();
//
//		String company = (String) claims.get("company");
//
//		System.out.println(company);

        return user;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        log.info("user id:{}", id);
        User user = new User();
        user.setId(id)
                .setUsername("billow")
                .setBirthday(new Date())
                .setPassword("123456");
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId("1")
                .setUsername("billow")
                .setBirthday(new Date())
                .setPassword("123456");
        users.add(user1);

        User user2 = new User();
        user2.setId("2")
                .setUsername("billow2")
                .setBirthday(new Date())
                .setPassword("44444");
        users.add(user2);
        return users;
    }


    @PostMapping
    public User createUser(User user) {
        log.info("user detail :{}", user);
        return user;
    }
}
