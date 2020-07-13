package com.atguigu.dao;

import com.atguigu.pojo.Order;

/**
 * @author jin
 */
public interface OrderDao {
    /**
     * 保存订单基本信息，订单详情 由OrderItemDao保存,通过orderId关联
     * @param order
     * @return  返回影响的行数，或 -1没有成功
     */
    public int saveOrder(Order order);
}
