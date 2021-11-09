package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.dao.RoleDao;
import com.example.demoJavaWebAll11.dao.impl.RoleDaoImpl;
import com.example.demoJavaWebAll11.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDaoImpl();

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
     * @param rolename 角色名称
     * @param state    角色状态
     * @param menuids
     * @return 影响数据库的行数
     */
    @Override
    public int addRole(String rolename, String state, String[] menuids) {
        // 1. 新增角色表
        Role role = new Role();
        role.setRoleName(rolename);
        role.setRoleState(Integer.parseInt(state));
        int add = roleDao.addRole(role);
        if (add > 0) {
            // 新增成功
        } else {
            // 新增失败
        }

        // TODO: 先测试角色新增功能
        return add;
        // 2. 新增中间表
        // 2.1. 如何获得新增数据的id

//        return roleDao.addRole(rolename, state, menuids);
    }
}
