package com.example.demoJavaWebAll11.dao;

import com.example.demoJavaWebAll11.bean.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 获取所有角色
     * @param pageIndex  管理页：第n页
     * @param pageSize   管理页：总页数
     * @return            角色列表
     */
    public List<Role> getRoleList(int pageIndex, int pageSize);

    /**
     * 获取所有角色的条数
     * @return  所有角色的条数
     */
    public int total();
}
