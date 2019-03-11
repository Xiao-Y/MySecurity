package com.billow.controller;

import com.billow.dto.User;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.common.Json;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
