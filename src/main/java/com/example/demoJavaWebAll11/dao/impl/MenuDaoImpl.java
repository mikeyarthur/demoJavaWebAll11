package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Menu;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.MenuDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl extends DBUtils implements MenuDao {
    /**
     * @return 查询菜单列表
     */
    @Override
    public List<Menu> getMenuList() {
        List<Menu> menuList = new ArrayList();
        try {
            String sql = "select * from menu";
            resultSet = query(sql, null);
            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUpmenuId(resultSet.getInt("upmenuid"));
                menu.setState(resultSet.getInt("state"));
                menu.setDesc(resultSet.getString("desc"));
                menu.setUrl(resultSet.getString("url"));

                menuList.add(menu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        
        return menuList;
    }
}
