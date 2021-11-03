package com.example.demoJavaWebAll11.dao;

import com.example.demoJavaWebAll11.bean.Users;

public interface UsersDao {
    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    public Users login(String username, String password);
}
