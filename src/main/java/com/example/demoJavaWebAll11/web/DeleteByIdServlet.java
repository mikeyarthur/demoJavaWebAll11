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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/Educational/student/deletebyid")
public class DeleteByIdServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String sid = req.getParameter("sid");

        // 调用service 主键查询
        StudentService service = new StudentServiceImpl();
        int result = service.deleteById(Integer.parseInt(sid));
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (result > 0) {
            // 转发
//            req.getRequestDispatcher("list.jsp").forward(req, resp);
            writer.println("<script>alert('删除成功');location.href='/Educational/student/getStudentList'</script>");
        } else {
            writer.println("<script>alert('删除失败');location.href='/Educational/student/getStudentList'</script>");
        }
    }
}
