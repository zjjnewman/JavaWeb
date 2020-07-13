package com.atguigu.web;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 结账是从购物车过来的，购物车保存在 服务器session中
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        // 用户登录后才可以结账，用户登录后，user信息也保存在session中
        // 浏览器发送请求过来 会把cookie里面设置的Id值也拿过来，req.getSession()通过 这个id值了查找是否存了 关联的session
        User loginUser = (User) req.getSession().getAttribute("user");

        // 如果用户没登录，转发到登录页面
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            // 转发或重定向后，后面的代码不用执行，直接return
            return;
        }

        // 生成订单号，并保存到req的域中
        String orderId = orderService.createOrder(cart, loginUser.getId());
        req.getSession().setAttribute("orderId", orderId);

        // 请求转发到 结算页面 pages/cart/checkout.jsp，为了防止表单重复提交，应该用重定向，req域失效，把orderId保存到session中
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
