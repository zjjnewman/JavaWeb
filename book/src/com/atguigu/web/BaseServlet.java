package com.atguigu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 优化 1 if else ，缺点，每来一个业务就要判断一次，考虑用反射
//        String action = req.getParameter("action");
//        if(action.equals("login")){
//            login(req, resp);
//        } else if(action.equals("regist")){
//            regist(req, resp);
//        }

        // 优化2 用反射方式获取业务
        String action = req.getParameter("action");
        try {
            Method method = UserServlet.class.getDeclaredMethod(action,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}