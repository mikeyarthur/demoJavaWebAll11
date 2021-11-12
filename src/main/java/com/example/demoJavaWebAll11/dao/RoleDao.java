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

    /**
     *
     * @param role  包含新增角色信息的对象
     * @return      影响数据库的条数
     */
    public int addRole(Role role);

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

    /**
     *
     * @param roleid    要修改对象的roleid
     * @param attr      要修改的属性值（数据库对应的列名）
     * @param value     要修改的值
     * @return          数据库更新影响的条数
     */
    public int set(int roleid, String attr, String value);
}
