package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.bean.Users;
import com.example.demoJavaWebAll11.dao.UsersDao;
import com.example.demoJavaWebAll11.dao.impl.UsersDaoImpl;
import com.example.demoJavaWebAll11.service.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao = new UsersDaoImpl();

    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    @Override
    public Users login(String username, String password) {
        return usersDao.login(username, password);
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
}
