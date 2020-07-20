package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    // 使用 DbUtils 操作数据库，线导入包
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 用来执行：insert update delete语句
     * @return 如果返回-1，说明执行失败<br/>返回其他表示影响的行数
     */
    public int update(String sql, Object ...args){

        Connection connection = JdbcUtils.getConnection();
        try {
            // 返回影响的行数
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean 的sql语句
     * @param type 返回的对象类型，
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean 的sql语句
     * @param type 返回的对象类型，
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql
     * @param args
     * @return 返回统计信息
     */
    public Object queryForSingleValue(String sql, Object ...args) {

        Connection connection = JdbcUtils.getConnection();

        try {
            return queryRunner.query(connection, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
