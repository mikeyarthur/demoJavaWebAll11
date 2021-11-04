package com.example.demoJavaWebAll11.web;

import com.example.demoJavaWebAll11.bean.Grade;
import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.service.GradeService;
import com.example.demoJavaWebAll11.service.StudentService;
import com.example.demoJavaWebAll11.service.impl.GradeServiceImpl;
import com.example.demoJavaWebAll11.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/Educational/student/findbyid")
public class FindByIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String sid = req.getParameter("sid");

        // 调用service 主键查询
        StudentService service = new StudentServiceImpl();
        Student student = service.findById(Integer.parseInt(sid));
        req.setAttribute("stu", student);

        // 年级列表查询
        GradeService gradeService = new GradeServiceImpl();
        List<Grade> list = gradeService.getList();
        req.setAttribute("glist", list);

        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }
}
