package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jin
 */
@Deprecated
public class RegisterServlet extends HttpServlet {

    /**
     * web层只能操作 service层，所以要 new 一个实例
     */
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);


            } else {
                // 可用 注册用户，插入数据库
                userService.registerUser(new User(null, username, password, email));
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
