package com.example.demoJavaWebAll11.service;

import com.example.demoJavaWebAll11.bean.Menu;

import java.util.List;

public interface MenuService {
    /**
     *
     * @return 查询菜单列表
     */
    public List<Menu> getMenuList();
}
