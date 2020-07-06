package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username = ?";
        User user = queryForOne(User.class, sql, username);
        return user;
    }

    /**
     * 根据用户名密码查询用户
     *
     * @param username
     * @param password
     * @return 如果为null，说明用户不存在
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username, password, email) values(?, ?, ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
