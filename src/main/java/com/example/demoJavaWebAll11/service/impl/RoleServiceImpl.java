package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Menu;
import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.dao.MiddleDao;
import com.example.demoJavaWebAll11.dao.RoleDao;
import com.example.demoJavaWebAll11.dao.impl.MiddleDaoImpl;
import com.example.demoJavaWebAll11.dao.impl.RoleDaoImpl;
import com.example.demoJavaWebAll11.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();
    private MiddleDao middleDao = new MiddleDaoImpl();

    /**
     * 获取所有角色
     *
     * @param pageIndex 管理页：第n页
     * @param pageSize  管理页：总页数
     * @return 角色列表
     */
    @Override
    public List<Role> getRoleList(int pageIndex, int pageSize) {
        return roleDao.getRoleList(pageIndex, pageSize);
    }

    /**
     * 获取所有角色的条数
     *
     * @return 所有角色的条数
     */
    @Override
    public int total() {
        return roleDao.total();
    }

    /**
     * 目前没有考虑数据库操作的事务问题，这里有2个数据库操作（1 update role表， 2 update middle表），如果一个成功另一个失败，需要回退处理
     * @param rolename 角色名称
     * @param state    角色状态
     * @param menuids
     * @return 影响数据库的行数
     */
    @Override
    public int addRole(String rolename, String state, String[] menuids) {
        int ok = 0;
        try {
            // 1. 新增角色表
            Role role = new Role();
            role.setRoleName(rolename);
            role.setRoleState(Integer.parseInt(state));
            // 返回值是 addRole 数据库update后的id，不抛出异常，而且key > 0 为新增成功，
            int key = roleDao.addRole(role);

            // 2. 新增中间表
            // 2.1. 如何获得新增数据的id
            int insertMiddle = middleDao.insertMiddle(key, menuids);

            ok = 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
        }

        return ok;
    }

    /**
     * @param roleid
     * @return 根据传入的roleid，查询得到封装Role信息的对象
     */
    @Override
    public Role findbyid(int roleid) {
        Role role = roleDao.findbyid(roleid);

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

        return role;
    }
}
