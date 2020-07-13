package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocalConnContainer = new ThreadLocal<Connection>();

    static {

        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties 属性配置文件
            /**
             * InputStream inputStream = ClassLoader.getSystemResourceAsStream("jdbc.properties")
             * 这里被这句代码坑了，以后就用下面的形式获取加载器
             */
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
                    //从流中加载数据
            properties.load(inputStream);
            //创建 数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从 ThreadLocal<Connection> 中获取 关闭自动提交事务的 数据库连接对象，若为空，则从数据库连接池中获取，并放入ThreadLocal<Connection>
     * @return 返回关闭自动提交事务的 数据库连接对象
     */
    public static Connection getConnection1(){
        Connection connection = threadLocalConnContainer.get();
        if(connection == null){
            try {
                connection = dataSource.getConnection();
                threadLocalConnContainer.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void commitAndClose(){
        Connection connection = threadLocalConnContainer.get();
        if (connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 为啥线程池技术就要移除？因为线程池名字是固定的几个，轮流运行不同功能的程序，
        threadLocalConnContainer.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = threadLocalConnContainer.get();
        if (connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 为啥线程池技术就要移除？因为线程池名字是固定的几个，轮流运行不同功能的程序，
        threadLocalConnContainer.remove();
    }




    //************************************************************************************************************
    /**
     * 获取数据库连接池中的连接
     */
    public static Connection getConnection(){
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn 返回null 说明获取连接失败
     */
    public static void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }




}
