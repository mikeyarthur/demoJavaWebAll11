package com.example.demoJavaWebAll11.service;

import com.example.demoJavaWebAll11.bean.Role;

import java.util.List;

public interface RoleService {
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

    /**
     *
     * @param rolename  角色名称
     * @param state     角色状态
     * @param menuids
     * @return          影响数据库的行数
     */
    public int addRole(String rolename, String state, String[] menuids);

    /**
     *
     * @param roleid
     * @return      根据传入的roleid，查询得到封装Role信息的对象
     */
    public Role findbyid(int roleid);

    /**
     *
     * @param roleid
     * @return      根据传入的roleid，删除role表影响数据库的条数
     */
    public int delete(int roleid);
}
