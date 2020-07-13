package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

/**
 * @author jin
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    /**
     * 保存订单基本信息，订单详情 由OrderItemDao保存,通过orderId关联
     * @param order
     * @return  返回影响的行数，或 -1没有成功
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `create_time`, `price`, `status`, `user_id`) values(?, ?, ?, ?, ?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
