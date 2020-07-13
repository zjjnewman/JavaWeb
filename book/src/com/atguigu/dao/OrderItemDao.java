package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

/**
 * @author jin
 */
public interface OrderItemDao {

    /**
     * 保存 订单详情
     * @param orderItem
     * @return  返回影响的行数，或 -1没有成功
     */
    int saveOrderItem(OrderItem orderItem);
}
