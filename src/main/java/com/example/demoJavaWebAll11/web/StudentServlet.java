package com.example.demoJavaWebAll11.web;

import com.example.demoJavaWebAll11.bean.Grade;
import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.service.GradeService;
import com.example.demoJavaWebAll11.service.StudentService;
import com.example.demoJavaWebAll11.service.impl.GradeServiceImpl;
import com.example.demoJavaWebAll11.service.impl.StudentServiceImpl;
import com.example.demoJavaWebAll11.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/Educational/student/studentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
//        if ("insert".equals(operation)) {
//            insertStu(req, resp);
//        }
        if (operation == null) {
            getStuList(req, resp);
            return;
        }
        switch (operation) {
            case "insertStu":
                insertStu(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "findById":
                findById(req, resp);
                break;
            case "updateStu":
                updateStu(req, resp);
                break;
            case "getGradeList":
                getGradeList(req, resp);
                break;
            default:
                getStuList(req, resp);
                break;
        }
    }


    protected void getStuList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取参数
        // 1.1. 模糊查找条件
        String stuname = req.getParameter("stuname");
        String stuno = req.getParameter("stuno");
        String sex = req.getParameter("sex");
        int sexIndex = (sex==null || sex.length() == 0) ? -1 : Integer.parseInt(sex);

        // 1.2. 分页数据（limit 开始位置 显示条数）
        // 页码值(当前页码值)
        String pageIndex = req.getParameter("pageIndex");
        // 如果页面没有传入 pageIndex 的值，则认为默认查询第一页
        int index = pageIndex==null ? 1 : Integer.parseInt(pageIndex);
        PageUtil pageUtil = new PageUtil();

        // 2. 调取service方法
        int pageSize = pageUtil.getPageSize();
        // 2.1. 模糊查询
        StudentService service = new StudentServiceImpl();
        List<Student> students = service.getStudents(stuname, stuno, sexIndex, index, pageSize);
        // 2.2. 总页数
        // 总页数 = 总条数 % 每页显示的条数 > 0 ? 总条数 / 每页显示条数 + 1 : 总条数 / 每页显示条数
        int total = service.total(stuname, stuno, sexIndex);                            // 总条数
        pageUtil.setTotal(total);
//        int totalPages = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;// 总页数
//        pageUtil.setTotalPages(totalPages);   // 可以，但没必要，因为直接在PageUtil工具类内部实现即可；
        pageUtil.setDataList(students);
        pageUtil.setPageIndex(index);

        // 3. 跳转页面
        // 如果后台想往前台传数据，需要在后台存值
        // 3.1. 模糊查询条件
        req.setAttribute("stulist", students);
        req.setAttribute("stuname", stuname);
        req.setAttribute("stuno", stuno);
        req.setAttribute("sex", sexIndex);
//        // 3.2. 存储分页数据
//        req.setAttribute("index", index);
//        // 3.3. 存储总条数
//        req.setAttribute("total", total);
//        req.setAttribute("totalPages", totalPages);
//        req.setAttribute("pageSize", pageSize);
        // 3.4. 改用存储pageUtil的对象
        req.setAttribute("p1", pageUtil);

        // 前面使用req.setAttribute，如果重定向数据就没了，所以使用转发的方式
        // 注意：这里list.jsp，是相对路径来的，意味着是，/Educational/student/list.jsp
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
    protected void getGradeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询年级列表
        GradeService service = new GradeServiceImpl();
        List<Grade> list = service.getList();

        // 存储属性
        req.setAttribute("glist", list);

        // 转发
        // 注意：add.jsp 是相对路径，意味着，是 /Educational/student/add.jsp
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }
    protected void insertStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        // 1. 注意编码
//        已经加了filter_encoding，这里可以省略
//        req.setCharacterEncoding("utf-8");
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
//            writer.println("<script>alert('新增成功');location.href='/Educational/student/getStudentList';</script>");
            writer.println("<script>alert('新增成功');location.href='/Educational/student/studentServlet';</script>");
        } else {
            // 新增失败
            // js方式，后台发送请求
//            writer.println("<script>alert('新增失败');location.href='/Educational/student/getGradeList';</script>");
            writer.println("<script>alert('新增失败');location.href='/Educational/student/studentServlet?operation=getGradeList';</script>");
        }
    }
    protected void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            writer.println("<script>alert('删除成功');location.href='/Educational/student/studentServlet'</script>");
        } else {
            writer.println("<script>alert('删除失败');location.href='/Educational/student/studentServlet'</script>");
        }
    }
    protected void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void updateStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        // 1. 注意编码
//        req.setCharacterEncoding("utf-8");
//        如果不处理乱码的话，运行时会报错误，可以通过web.xml增加filter_encoding的连接，或者filter加元注解
//        com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'stuname' at row 1
//        com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'address' at row 1


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
            writer.println("<script>alert('修改成功');location.href='/Educational/student/studentServlet'</script>");
        } else {
            writer.println("<script>alert('修改失败');location.href='/Educational/student/studentServlet?operation=findbyid&sid=" + sid + "'</script>");
        }
    }
}
