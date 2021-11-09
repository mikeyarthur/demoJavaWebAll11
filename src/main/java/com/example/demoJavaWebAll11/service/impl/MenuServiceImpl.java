package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Menu;
import com.example.demoJavaWebAll11.dao.MenuDao;
import com.example.demoJavaWebAll11.dao.impl.MenuDaoImpl;
import com.example.demoJavaWebAll11.service.MenuService;

import java.util.ArrayList;
import java.util.List;

public class MenuServiceImpl implements MenuService {
    MenuDao menuDao = new MenuDaoImpl();
    /**
     * @return 查询菜单列表
     */
    @Override
    public List<Menu> getMenuList() {
        // 1. service层对dao层的数据进行处理
//        return menuDao.getMenuList();

        // 1.1. 未分一二级的菜单
        List<Menu> menuList = menuDao.getMenuList();
        // 1.2. 分级后的菜单
        List<Menu> gradedMenuList = new ArrayList<>();

        for (Menu menu : menuList) {
            if (menu.getUpmenuId() == 0) {
                List<Menu> secondMenuList = new ArrayList<>();
                // 一级菜单，继续匹配二级菜单
                for (Menu secondMenu : menuList) {
                    if (secondMenu.getUpmenuId() == menu.getMenuId()) {
                        secondMenuList.add(secondMenu);
                    }
                }
                menu.setSecondMenuList(secondMenuList);
                gradedMenuList.add(menu);
            }
        }

        return gradedMenuList;
    }
}
