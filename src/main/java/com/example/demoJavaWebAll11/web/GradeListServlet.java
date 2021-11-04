package com.example.demoJavaWebAll11.web;



import com.example.demoJavaWebAll11.bean.Grade;
import com.example.demoJavaWebAll11.service.GradeService;
import com.example.demoJavaWebAll11.service.impl.GradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/Educational/student/getGradeList")
public class GradeListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询年级列表
        GradeService service = new GradeServiceImpl();
        List<Grade> list = service.getList();

        // 存储属性
        req.setAttribute("glist", list);

        // 转发
        // 注意：add.jsp 是相对路径，意味着，是 /Educational/student/add.jsp
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }
}
