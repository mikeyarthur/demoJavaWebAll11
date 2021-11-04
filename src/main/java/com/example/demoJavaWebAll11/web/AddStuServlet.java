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
import java.io.PrintWriter;

@WebServlet(value = "/Educational/student/addStu")
public class AddStuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 注意编码
        req.setCharacterEncoding("utf-8");
        // 2. 获取表单数据
        String stuNo = req.getParameter("stuNo");
        String stuName = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idNumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introduction = req.getParameter("introduction");



        // 3. 调用service，操作数据库
        Student student = new Student();
        student.setStuNo(stuNo);
        student.setStuName(stuName);
        student.setSex(Integer.parseInt(sex));
        student.setGid(Integer.parseInt(gid));
        student.setEmail(email);
        student.setPhone(phone);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setPolitics(politics);
        student.setIdNumber(idNumber);
        student.setProfession(profession);
        student.setIntroduction(introduction);

        StudentService service = new StudentServiceImpl();
        int result = service.insertStu(student);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (result > 0) {
            // 新增成功
            // js方式，后台发送请求
            writer.println("<script>alert('新增成功');location.href='/Educational/student/getStudentList';</script>");
        } else {
            // 新增失败
            // js方式，后台发送请求
            writer.println("<script>alert('新增失败');location.href='/Educational/student/getGradeList';</script>");
        }
    }
}
