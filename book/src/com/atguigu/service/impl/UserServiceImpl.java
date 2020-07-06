package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @author jin
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 检查登录名是否可用
     *
     * @param username
     * @return 返回true 说明有这个用户名，不能用它注册
     */
    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
