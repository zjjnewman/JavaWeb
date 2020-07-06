package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    public User queryUserByUsername(String name);

    /**
     * 根据用户名密码查询用户
     * @param username
     * @param password
     * @return 如果为null，说明用户不存在
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);



}
