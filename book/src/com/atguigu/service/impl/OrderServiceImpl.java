package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.sql.Timestamp;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    /**
     * 通过购物车，创建订单
     *  @param cart
     * @param userId
     * @return 返回订单号
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号  唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        // 保存订单
        Order order = new Order(orderId, new Timestamp(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);

        // 以下应该做成事务

        orderDao.saveOrder(order);

        // 获取购物车中每一个商品项，然后保存到 orderItem
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            // 获取购物车商品项
            CartItem cartItem = entry.getValue();
            // 转成订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 保存订单详情
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
