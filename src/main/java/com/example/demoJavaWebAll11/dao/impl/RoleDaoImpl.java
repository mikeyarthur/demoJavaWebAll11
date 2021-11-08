package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.RoleDao;

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
}
