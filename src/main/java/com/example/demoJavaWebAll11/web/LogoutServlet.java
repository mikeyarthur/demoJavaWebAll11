package com.example.demoJavaWebAll11.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LogoutServlet.service ----- start");
        // 1. 清除session
        req.getSession().invalidate();

        // 2. 跳转页面
//        // 2.1. 方法一：只有frameset的顶部退出，没有退出整个页面
//        resp.sendRedirect("/login.jsp");

        // 2.2. 方法二：frameset的顶部和中部都能退出
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        // top.location.href 回到框架的顶部
        writer.println("<script>alert('退出成功');top.location.href='login.jsp';</script>");
//        // location.href 仅退出当前框架集，而不是整个页面
//        writer.println("<script>alert('退出成功');location.href='login.jsp';</script>");

        System.out.println("LogoutServlet.service ----- end");
    }
}
