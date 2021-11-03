package com.example.demoJavaWebAll11.web;

import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.service.StudentService;
import com.example.demoJavaWebAll11.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/Educational/student/getStudentList")
public class GetStudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取参数
        String stuname = req.getParameter("stuname");
        String stuno = req.getParameter("stuno");
        String sex = req.getParameter("sex");
        int sexIndex = sex==null ? -1 : Integer.parseInt(sex);

        // 2. 调取service方法
        StudentService service = new StudentServiceImpl();
        List<Student> students = service.getStudents(stuname, stuno, sexIndex);

        // 3. 跳转页面
        // 如果后台想往前台传数据，需要在后台存值
        req.setAttribute("stulist", students);
        req.setAttribute("stuname", stuname);
        req.setAttribute("stuno", stuno);
        req.setAttribute("sex", sexIndex);
        // 前面使用req.setAttribute，如果重定向数据就没了，所以使用转发的方式
        // 注意：这里list.jsp，是相对路径来的，意味着是，/Educational/student/list.jsp
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}