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
        else if ("queryBeforeEditRole".equals(operation)) {
            queryBeforeEditRole(req, resp);
        }
        else if ("editRole".equals(operation)) {
            editRole(req, resp);
        }
        else if ("delete".equals(operation)) {
            delete(req, resp);
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
        int update = getUpdate(req);
        // 3. 存值跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (update > 0) {
            writer.println("<script>alert('新增成功');location.href='roles?operation=select';</script>");
        } else {
            writer.println("<script>alert('新增失败');location.href='roles?operation=selectmenus&nextOperation=addRole';</script>");
        }
    }

    private int getUpdate(HttpServletRequest req) {
        // 1. 接收参数
        String rolename = req.getParameter("rolename");
//        System.out.println("rolename = " + rolename);
        String state = req.getParameter("state");
//        System.out.println("state = " + state);
        // 这里得到是一个列表，而不是一个单一的值
        String[] menuids = req.getParameterValues("namemenu");
//        System.out.println("menuids = " + menuids);
//        for (String menuid : menuids) {
//            System.out.println("menuid = " + menuid);
//        }

        // 2. 调取service
        return roleService.addRole(rolename, state, menuids);
    }

    protected void queryBeforeEditRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextOperation = req.getParameter("nextOperation");
        Role roleToFind = getRole(req);

        // 2.2. 同时查询全部的菜单列表-修改页面要默认选中当前角色的菜单列表
        Role roleToEdit = roleService.findbyid(roleToFind.getRoleId());
//        req.setAttribute("rolename", roleToEdit.getRoleName());
////        System.out.println("roleToEdit.getRoleName() = " + roleToEdit.getRoleName());
//        req.setAttribute("rolestate", roleToEdit.getRoleState());
////        System.out.println("roleToEdit.getRoleState() = " + roleToEdit.getRoleState());
//        req.setAttribute("rolemenulist", roleToEdit.getMenuList());
////        System.out.println("roleToEdit.getMenuList() = " + roleToEdit.getMenuList());
        // 直接存role对象，后续在edit提交的时候删除
        req.setAttribute("role", roleToEdit);

        List<Menu> roleMenuList = roleToEdit.getMenuList();
        List<Menu> allMenuList = menuService.getMenuList();

        for (int i = 0; i < roleMenuList.size(); i++) {
//            System.out.println("roleMenuList.get(" + i + ") = " + roleMenuList.get(i) + "\n\n");
            for (int j = 0; j < allMenuList.size(); j++) {
                if (allMenuList.get(j).getMenuId() == roleMenuList.get(i).getMenuId()) {
                    allMenuList.get(j).setCheckedType(1);
                    break;
                }
            }
            List<Menu> secondRoleMenuList = roleMenuList.get(i).getSecondMenuList();
            if (secondRoleMenuList != null) {
                for (int j = 0; j < secondRoleMenuList.size(); j++) {
//                    System.out.println("secondMenuList.get(" + j + ")= " + secondRoleMenuList.get(j) + "\n\n");
                    for (int z = 0; z < allMenuList.size(); z++) {
                        List<Menu> secondAllMenuList = allMenuList.get(z).getSecondMenuList();
                        for (int k = 0; k < secondAllMenuList.size(); k++) {
                            if (secondAllMenuList.get(k).getMenuId() == secondRoleMenuList.get(j).getMenuId()) {
                                secondAllMenuList.get(k).setCheckedType(1);
                                allMenuList.get(z).setCheckedType(1);
                                break;
                            }
                        }
                    }
                }
            }
        }


//        System.out.println("allMenuList = " + allMenuList);

        req.setAttribute("allmenulist", allMenuList);
//        System.out.println("allmenulist = " + allMenuList);

        // 3. 跳转edit.jsp
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
//        writer.println("<script>alert('进入edit.jsp');location.href='edit.jsp';</script>");

        if ("editRole".equals(nextOperation)) {
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } else if ("infoRole".equals(nextOperation)) {
            req.getRequestDispatcher("info.jsp").forward(req, resp);
        } else {
            writer.println("<script>alert('Not perform as expected, please re-check!');</script>");
        }
    }

    private Role getRole(HttpServletRequest req) {
        // 1. 获取jsp参数和req参数，确定roleid
        String pageIndex = req.getParameter("index");
//        System.out.println("pageIndex = " + pageIndex);
        String listIndex = req.getParameter("listIndex");
//        System.out.println("listIndex = " + listIndex);


        // 2. 处理数据
        // 2.1. 主键查询：角色信息，包含的菜单的id（middle）--多表查询
        PageUtil pageUtil = new PageUtil();
        List<Role> usersList = roleService.getRoleList(Integer.parseInt(pageIndex), pageUtil.getPageSize());
        Role roleToFind = usersList.get(Integer.parseInt(listIndex) - 1);
        return roleToFind;
    }

    protected void editRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 缓存旧的role，后面删除用的
        String roleid = req.getParameter("roleid");
//        System.out.println("roleid = " + roleid);
        // 2. 先新增新的，后删除旧的，第一步失败了直接返回页面，不影响原来的数据库
        int update = getUpdate(req);

        // 3. 存值跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (update > 0) {
            // 新增新的role成功
            int delete = roleService.delete(Integer.parseInt(roleid));
            if (delete > 0) {
                // 删除旧的role成功，跳转到select页面
                writer.println("<script>alert('更新成功');location.href='roles?opertaion=select'</script>");
            } else {
                System.out.println("RoleServlet.editRole error");
                writer.println("<script>alert('更新失败，数据出错，但我不知道怎么搞了，删库跑路了');console.log('更新失败，数据出错，但我不知道怎么搞了，删库跑路了')</script>");
            }
        } else {
            // 失败，重返 edit.jsp
            // queryBeforeEditRole(req, resp);  // 错误操作，获取参数都是null，后续的参数处理会抛异常的，需要回到页面提req
            // 正确操作是：失败后应该要返回到列表
            writer.println("<script>alert('更新失败');location.href='roles?opertaion=select'</script>");
            return;
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role roleToDelete = getRole(req);

        // 2. 调取service
        // 2.1. 先删除middle表
        // 2.2. 再删除role表
        // 琐碎的工作，在service层做，不在应用层做
        int delete = roleService.delete(roleToDelete.getRoleId());

        // 3. 存值跳转页面
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if (delete > 0) {
            writer.println("<script>alert('更新成功');location.href='roles?opertaion=select'</script>");
        } else {
            writer.println("<script>alert('更新失败');location.href='roles?opertaion=select'</script>");
        }
    }
}
