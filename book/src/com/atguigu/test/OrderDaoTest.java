package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.sql.Date;

class OrderDaoTest {

    @Test
    void saveOrder() throws ParseException {
        System.out.println(new Timestamp(System.currentTimeMillis()));
        OrderDao orderDao = new OrderDaoImpl();
        int i = orderDao.saveOrder(new Order("1988", new Timestamp(System.currentTimeMillis()), new BigDecimal(10), null,1));
        System.out.println(i);

//        System.out.println(new java.util.Date().);
//        System.out.println(new java.sql.Date(new java.util.Date().getTime()));
//        System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date sqlData = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
//        System.out.println(sqlData);
    }
}