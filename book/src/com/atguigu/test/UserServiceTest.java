package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    void registerUser() {
        userService.registerUser(new User(null, "bbj168", "123", "bbj168@qq.com"));
    }

    @Test
    void login() {
        User user = userService.login(new User(null, "admin", "admin", null));
        System.out.println(user);
    }

    @Test
    void existsUsername() {
        System.out.println(userService.existsUsername("wzg168"));

    }
}