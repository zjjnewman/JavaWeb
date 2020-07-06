package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {

    UserService userService =  new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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


    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求参数

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2. 检查验证码是否正确， 测试阶段固定为：abcde
        String c = "abcde";
        if(c.equalsIgnoreCase(code)){

            // 3.检查用户名是否正确
            if(userService.existsUsername(username)){

                //把回显信息保存在request域中
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                // 用户名已存在，不可用 跳回注册页面
                System.out.println("用户名" + "[" +username + "]" + "已存在");
                // 转发到注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {

                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                // 可用 注册用户，插入数据库
                userService.registerUser(user);
                // 转发到注册成功
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {

            //把回显信息保存在request域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            // 不正确跳回注册页面
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

        }
    }
}
