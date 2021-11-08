package com.example.demoJavaWebAll11.web;

import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.bean.Users;
import com.example.demoJavaWebAll11.service.impl.UsersServiceImpl;
import com.example.demoJavaWebAll11.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/power/user/users")
public class UsersServlet extends HttpServlet {

    private UsersServiceImpl usersService = new UsersServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("select".equals(operation)) {
            select(req, resp);
        } else if ("getRoleList".equals(operation)) {
            getRoleList(req, resp);
        }
    }

    // 查询数据（分页），没有模糊查询的条件
    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收参数（分页参数+模糊查参数）
        String index = req.getParameter("index");
        int pageIndex = (index == null || index.length() == 0) ? 1 : Integer.parseInt(index);

        // 2. 调取service方法
        // 2.1. 查询数据列表的方法
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageIndex(pageIndex);
        List<Users> usersList = usersService.getUsersList(pageUtil.getPageIndex(), pageUtil.getPageSize());

        // 2.2. 查询总条数的方法
        int total = usersService.total();
        pageUtil.setTotal(total);
        pageUtil.setDataList(usersList);

        // 3. 存值跳转页面
        req.setAttribute("pi", pageUtil);
        // 注意：转发路径不要带"/", 带"/"的情况是从根目录的路径下开始查找
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    protected void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收参数
        String nextOperation = req.getParameter("nextOperation");

        // 2. 调用service方法
        List<Role> roleList = usersService.getRoleList();
        req.setAttribute("rlist", roleList);

        // 3. 存值和跳转页面
        // 注意：转发路径不要带"/", 带"/"的情况是从根目录的路径下开始查找，不带"/"的情况是 /power/user/add.jsp
        if ("addUser".equals(nextOperation)) {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>alert('Not perform as expected, please re-check!');</script>");
        }
    }
}
