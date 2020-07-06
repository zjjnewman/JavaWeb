package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author jin
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null 说明登录失败
     */
    public User login(User user);

    /**
     * 检查登录名是否可用
     * @param username
     * @return 返回true 说明没有这个用户名，可以用它注册
     */
    public boolean existsUsername(String username);
}
