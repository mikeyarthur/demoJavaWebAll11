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

@WebServlet(value = "/Educational/student/updateStu")
public class UpdateStuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 注意编码
        req.setCharacterEncoding("utf-8");
        // 2. 获取表单数据
        String sid = req.getParameter("sid");
        String stuName = req.getParameter("stuName");
        String stuNo = req.getParameter("stuNo");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String profession = req.getParameter("profession");
        String idNumber = req.getParameter("idNumber");
        String politics = req.getParameter("politics");
//        String state = req.getParameter("state");
        String introduction = req.getParameter("introduction");
        String gid = req.getParameter("gid");

        Student student = new Student();
        student.setStuId(Integer.parseInt(sid));
        student.setStuName(stuName);
        student.setStuNo(stuNo);
        student.setSex(Integer.parseInt(sex));
        student.setPhone(phone);
        student.setEmail(email);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setProfession(profession);
        student.setIdNumber(idNumber);
        student.setPolitics(politics);
        student.setIntroduction(introduction);
        student.setGid(Integer.parseInt(gid));

        // 3. 调用service
        StudentService service = new StudentServiceImpl();
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        int result = service.updateStu(student);
        if (result > 0) {
            // 转发
//            req.getRequestDispatcher("list.jsp").forward(req, resp);
            writer.println("<script>alert('修改成功');location.href='/Educational/student/getStudentList'</script>");
        } else {
            writer.println("<script>alert('修改失败');location.href='/Educational/student/findbyid?sid=" + sid + "'</script>");
        }
    }
}
