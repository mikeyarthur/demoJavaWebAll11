package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Menu;
import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.RoleDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends DBUtils implements RoleDao {
    /**
     * 获取所有角色
     *
     * @param pageIndex 管理页：第n页
     * @param pageSize  管理页：总页数
     * @return 角色列表
     */
    @Override
    public List<Role> getRoleList(int pageIndex, int pageSize) {
        List roleList = new ArrayList();
        try {
            String sql = "select * from role limit ?,?";
            List params = new ArrayList();
            params.add((pageIndex - 1) * pageSize);
            params.add(pageSize);

            resultSet = query(sql, params);
            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));

                roleList.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return roleList;
    }

    /**
     * 获取所有角色的条数
     *
     * @return 所有角色的条数
     */
    @Override
    public int total() {
        int total = 0;
        try {
            String sql = "select count(1) from role";
            resultSet = query(sql, null);
            while (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return total;
    }

    /**
     *
     * @param role  包含新增角色信息的对象
     * @return      影响数据库的条数
     */
    public int addRole(Role role) {
        int key = 0;
        try {
            List params = new ArrayList();
            String sql = "insert into role values (null, ?, ?)";
            params.add(role.getRoleName());
            params.add(role.getRoleState());

            // update保存的是受影响的数据库条数，update失败会抛出异常，所以不用判断return，可以直接下一步获取新增后的id值
            int update = update(sql, params);
            // pps获取ResultSet，ResultSet中只有一个值，就是新增后的id值
            ResultSet generatedKeys = pps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return key;
    }

    /**
     * @param roleid
     * @return 根据传入的roleid，查询得到封装Role信息的对象
     */
    @Override
    public Role findbyid(int roleid) {
        Role role = new Role();
        List menuList = new ArrayList();
        try {
            //SELECT * FROM role r, menu m, middle mid WHERE r.roleid=mid.roleid AND mid.menuid=m.menuid AND r.roleid=1;
            String sql = "SELECT * FROM role r, menu m, middle mid WHERE r.roleid=mid.roleid AND mid.menuid=m.menuid AND r.roleid=?";
            List params = new ArrayList();
            params.add(roleid);

            resultSet = query(sql, params);
            while (resultSet.next()) {
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));

                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUrl(resultSet.getString("url"));
                menu.setState(resultSet.getInt("state"));

                menuList.add(menu);
            }

            role.setMenuList(menuList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        // 用户包含角色，角色包含菜单

        return null;
    }
}
