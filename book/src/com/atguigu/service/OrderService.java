package com.atguigu.service;

import com.atguigu.pojo.Cart;

/**
 * @author jin
 */
public interface OrderService {
    /**
     * 通过购物车，创建订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId);
}
