package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Users;
import com.example.demoJavaWebAll11.dao.UsersDao;
import com.example.demoJavaWebAll11.dao.impl.UsersDaoImpl;
import com.example.demoJavaWebAll11.service.UsersService;

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
}
