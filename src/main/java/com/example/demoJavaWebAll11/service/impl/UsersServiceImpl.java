package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Menu;
import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.bean.Users;
import com.example.demoJavaWebAll11.dao.RoleDao;
import com.example.demoJavaWebAll11.dao.UsersDao;
import com.example.demoJavaWebAll11.dao.impl.RoleDaoImpl;
import com.example.demoJavaWebAll11.dao.impl.UsersDaoImpl;
import com.example.demoJavaWebAll11.service.UsersService;

import java.util.ArrayList;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao = new UsersDaoImpl();
    private RoleDao roleDao = new RoleDaoImpl();

    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    @Override
    public Users login(String username, String password) {
        // 用户包含角色，角色包含菜单，菜单分一二级
        Users users = usersDao.login(username, password);
//        System.out.println("users = " + users);
        if (users == null) {
//            System.out.println("users = " + users);
            return users;
        }
        // 根据角色id查询角色，菜单信息（三表联查），users.roleid ==> middleid ==> menuids
        int roleId = users.getRoleId();
//        System.out.println("roleId = " + roleId);
        Role role = roleDao.findbyid(roleId);

        // 对获取的role对象的菜单进行一二级分级
        List<Menu> menuList = role.getMenuList();
        List<Menu> gradedMenuList = new ArrayList<>();

        for (Menu menu : menuList) {
            if (menu.getUpmenuId() == 0) {
                List<Menu> secondMenuList = new ArrayList<>();
                // 一级菜单，继续匹配二级菜单
                for (Menu secondMenu : menuList) {
                    if (secondMenu.getUpmenuId() == menu.getMenuId()) {
                        secondMenuList.add(secondMenu);
                    }
                }
                menu.setSecondMenuList(secondMenuList);
                gradedMenuList.add(menu);
            }
        }
        role.setMenuList(gradedMenuList);
        users.setRole(role);

        return users;
    }

    /**
     * 查询用户列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Users> getUsersList(int pageIndex, int pageSize) {
        return usersDao.getUsersList(pageIndex, pageSize);
    }

    /**
     * 没有模糊查找条件，传参为空
     *
     * @return 查询总条数
     */
    @Override
    public int total() {
        return usersDao.total();
    }

    /**
     * @return 获取角色列表
     */
    @Override
    public List<Role> getRoleList() {
        return usersDao.getRoleList();
    }

    /**
     * 新增用户
     *
     * @param user 包含用户信息的对象
     * @return 影响的数据库条数
     */
    @Override
    public int addUser(Users user) {
        return usersDao.addUser(user);
    }
}
