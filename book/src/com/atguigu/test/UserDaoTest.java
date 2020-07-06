package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    void queryUserByUsername() {
        if(userDao.queryUserByUsername("wzg168") == null){
            return;
        }
//        User user= userDao.queryUserByUsername("wzg168");
//        System.out.println(user);
    }


    @Test
    void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        System.out.println(user);
    }

    @Test
    void saveUser() {
        User user = new User(null, "wzg168", "123456", "wzg168@qq.com");
        System.out.println(userDao.saveUser(user));
    }
}