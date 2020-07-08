package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/**
 * @author jin
 */
@Deprecated
public class LoginServlet extends HttpServlet {

    UserService userService =  new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        /**
         * 1.获取请求参数
         * 2.调用XXXservice处理业务
         * 3.根据login方法判断是否登录成功
         *      3.1成功
         *          跳转到login_success.html
         *      3.2失败
         *          跳转到登录页面
         */

        // 1.
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2.
        User user = userService.login(new User(null, username, password, null));
        // 3.1
        if(user == null){
            System.out.println("登录失败");
            // 把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {//3.2
            System.out.println("登陆成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }
}
