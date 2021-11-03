package com.example.demoJavaWebAll11.web;

import com.example.demoJavaWebAll11.bean.Users;
import com.example.demoJavaWebAll11.service.UsersService;
import com.example.demoJavaWebAll11.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//        System.out.println("LoginServlet.service +++++ start");
        // 1. 接收参数
        String username = req.getParameter("TxtUserName");
        String password = req.getParameter("TxtPassword");

        // 2. 调取service
        UsersService usersService = new UsersServiceImpl();
        Users users = usersService.login(username, password);

        // 3. 跳转页面
        if (users == null) {
            // 以弹窗方式提示用户，登录失败
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
//            writer.println("<script>console.log('用户名或密码不正确');</script>");
            // 使用firefox无法正常弹窗alert，但使用google浏览器可以，可能安装了屏蔽广告插件的原因
            writer.println("<script>location.href='login.jsp';alert('用户名或密码不正确');</script>");
//            writer.println("<script>location.href='#';alert('用户名或密码不正确');</script>");
//            writer.println("<script>alert('用户名或密码不正确');</script>");
//            System.out.println("用户名或密码不正确");
        } else {
            // 跳转到主页面
            // 1. 保存用户信息
            req.getSession().setAttribute("u1", users);
            resp.sendRedirect("index.jsp");
        }

//        System.out.println("LoginServlet.service +++++ end");
    }
}
