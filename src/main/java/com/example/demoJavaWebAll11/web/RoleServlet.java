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
//        else if ("addUser".equals(operation)) {
//            addUser(req, resp);
//        }
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
        System.out.println("menuList = " + menuList);
        for (Menu menu : menuList) {
            System.out.println("menu = " + menu);
        }
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

//    protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 1. 接收参数
//        String loginname = req.getParameter("loginname");
//        String password = req.getParameter("password");
//        String realname = req.getParameter("realname");
//        String sex = req.getParameter("sex");
//        String email = req.getParameter("email");
//        String phone = req.getParameter("phone");
//        String address = req.getParameter("address");
//        String cardid = req.getParameter("cardid");
//        String desc = req.getParameter("desc");
//        String roleId = req.getParameter("roleId");
//
//
//        Users users = new Users();
//        users.setLoginName(loginname);
//        users.setPassWord(password);
//        users.setRealName(realname);
//        users.setSex(Integer.parseInt(sex));
//        users.setEmail(email);
//        users.setAddress(address);
//        users.setPhone(phone);
//        users.setCardId(cardid);
//        users.setDesc(desc);
//        users.setRoleId(Integer.parseInt(roleId));
//
//        // 2. 调取service
//        int update = usersService.addUser(users);
//        // 3. 存值跳转页面
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = resp.getWriter();
//        if (update > 0) {
//            writer.println("<script>alert('新增成功');location.href='/power/user/users?operation=select';</script>");
//        } else {
//            writer.println("<script>alert('新增失败');location.href='/power/user/users?operation=getRoleList&nextOperation=addUser';</script>");
//        }
//    }
}
