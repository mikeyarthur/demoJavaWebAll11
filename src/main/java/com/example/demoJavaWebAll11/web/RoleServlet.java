package com.example.demoJavaWebAll11.web;

import com.example.demoJavaWebAll11.bean.Menu;
import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.service.MenuService;
import com.example.demoJavaWebAll11.service.RoleService;
import com.example.demoJavaWebAll11.service.impl.MenuServiceImpl;
import com.example.demoJavaWebAll11.service.impl.RoleServiceImpl;
import com.example.demoJavaWebAll11.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/power/role/roles")
public class RoleServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("select".equals(operation)) {
            select(req, resp);
        } else if ("selectmenus".equals(operation)) {
            selectmenus(req, resp);
        }
        else if ("addRole".equals(operation)) {
            addRole(req, resp);
        }
        else {
            select(req, resp);
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
        List<Role> usersList = roleService.getRoleList(pageUtil.getPageIndex(), pageUtil.getPageSize());

        // 2.2. 查询总条数的方法
        int total = roleService.total();
        pageUtil.setTotal(total);
        pageUtil.setDataList(usersList);

        // 3. 存值跳转页面
        req.setAttribute("pi", pageUtil);
        // 注意：转发路径不要带"/", 带"/"的情况是从根目录的路径下开始查找
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    protected void selectmenus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收参数
        String nextOperation = req.getParameter("nextOperation");

        // 2. 调用service方法
        List<Menu> menuList = menuService.getMenuList();
//        System.out.println("menuList = " + menuList);
//        for (Menu menu : menuList) {
//            System.out.println("menu = " + menu);
//        }
        req.setAttribute("mlist", menuList);

        // 3. 存值和跳转页面
        // 注意：转发路径不要带"/", 带"/"的情况是从根目录的路径下开始查找，不带"/"的情况是 /power/user/add.jsp
        if ("addRole".equals(nextOperation)) {
            req.getRequestDispatcher("add.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>alert('Not perform as expected, please re-check!');</script>");
        }
    }

    protected void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收参数
        String rolename = req.getParameter("rolename");
        String state = req.getParameter("state");
        // 这里得到是一个列表，而不是一个单一的值
        String[] menuids = req.getParameterValues("namemenu");

        // 2. 调取service
        int update = roleService.addRole(rolename, state, menuids);
        // 3. 存值跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (update > 0) {
            writer.println("<script>alert('新增成功');location.href='roles?operation=select';</script>");
        } else {
            writer.println("<script>alert('新增失败');location.href='roles?operation=selectmenus&nextOperation=addRole';</script>");
        }
    }
}
