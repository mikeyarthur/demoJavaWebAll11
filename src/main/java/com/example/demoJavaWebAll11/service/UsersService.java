package com.example.demoJavaWebAll11.service;

import com.example.demoJavaWebAll11.bean.Users;

public interface UsersService {
    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    public Users login(String username, String password);
}
