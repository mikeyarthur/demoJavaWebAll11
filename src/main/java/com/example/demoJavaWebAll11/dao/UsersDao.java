package com.example.demoJavaWebAll11.dao;

import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.bean.Users;

import java.util.List;

public interface UsersDao {
    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    public Users login(String username, String password);

    /**
     * 查询用户列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Users> getUsersList(int pageIndex, int pageSize);

    /**
     * 没有模糊查找条件，传参为空
     * @return 查询总条数
     */
    public int total();

    /**
     *
     * @return 获取角色列表
     */
    public List<Role> getRoleList();
}
