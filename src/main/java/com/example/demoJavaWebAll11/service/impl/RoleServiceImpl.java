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
}
