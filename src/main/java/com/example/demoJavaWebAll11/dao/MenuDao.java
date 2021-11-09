package com.example.demoJavaWebAll11.dao;

import com.example.demoJavaWebAll11.bean.Menu;

import java.util.List;

public interface MenuDao {
    /**
     *
     * @return 查询菜单列表
     */
    public List<Menu> getMenuList();
}
