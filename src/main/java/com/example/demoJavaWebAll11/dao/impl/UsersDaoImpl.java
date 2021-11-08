package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Role;
import com.example.demoJavaWebAll11.bean.Users;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.UsersDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl extends DBUtils implements UsersDao {
    /**
     * 登录方法
     *
     * @param username
     * @param password
     */
    @Override
    public Users login(String username, String password) {
        Users users = null;
        try {
            String sql = "select * from users where `loginname`=? and `password`=?";
            ArrayList arrayList = new ArrayList<String>();
            arrayList.add(username);
            arrayList.add(password);
//            System.out.println("username = " + username);
//            System.out.println("password = " + password);
//            System.out.println("arrayList = " + arrayList);
            resultSet = query(sql, arrayList);

            if (resultSet == null) {
//                System.out.println("sql = " + sql);
//                System.out.println("resultSet = " + resultSet);
                return users;
            }
            while (resultSet.next()) {
                users = new Users();
                users.setLoginName(username);
                users.setRealName(resultSet.getString("realname"));
                users.setUserId(resultSet.getInt("userid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return users;
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
        // 这里不用null
        List<Users> usersList = new ArrayList<Users>();
        try {
            // 看 页面，是两表联查的数据
            // SELECT * FROM users u, role r WHERE u.roleid=r.roleid;
            // SELECT userid, loginname, realname, rolename FROM users u, role r WHERE u.roleid=r.roleid;
//        String sql = "select * from users limit ?,?";
            String sql = "SELECT userid, loginname, realname, rolename FROM users u, role r WHERE u.roleid=r.roleid limit ?,?";
            List params = new ArrayList();
            params.add((pageIndex - 1) * pageSize);
            params.add(pageSize);

            resultSet = query(sql, params);
            while (resultSet.next()) {
                // 1. 取出各表的数据
                Users users = new Users();
                users.setUserId(resultSet.getInt("userid"));
                users.setLoginName(resultSet.getString("loginname"));
                users.setRealName(resultSet.getString("realname"));

                Role role = new Role();
                role.setRoleName(resultSet.getString("rolename"));

                // 2. 建立关系
                users.setRole(role);

                usersList.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return usersList;
    }

    /**
     * 没有模糊查找条件，传参为空
     *
     * @return 查询总条数
     */
    @Override
    public int total() {
        int total = 0;
        try {
            // 看 页面，是两表联查的数据
            // SELECT * FROM users u, role r WHERE u.roleid=r.roleid;
            // SELECT userid, loginname, realname, rolename FROM users u, role r WHERE u.roleid=r.roleid;
//        String sql = "select * from users limit ?,?";
//            String sql = "SELECT userid, loginname, realname, rolename FROM users u, role r WHERE u.roleid=r.roleid limit ?,?";
//            String sql = "SELECT count(*) FROM users u, role r WHERE u.roleid=r.roleid limit ?,?";
            String sql = "SELECT count(1) FROM users u, role r WHERE u.roleid=r.roleid";
//            List params = new ArrayList();
//            params.add(pageIndex);
//            params.add(pageSize);

//            resultSet = query(sql, params);
            resultSet = query(sql, null);
            while (resultSet.next()) {
//                // 1. 取出各表的数据
//                Users users = new Users();
//                users.setUserId(resultSet.getInt("userid"));
//                users.setLoginName(resultSet.getString("loginname"));
//                users.setRealName(resultSet.getString("realname"));
//
//                Role role = new Role();
//                role.setRoleName(resultSet.getString("rolename"));
//
//                // 2. 建立关系
//                users.setRole(role);
//
//                usersList.add(users);
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
     * @return 获取角色列表
     */
    @Override
    public List<Role> getRoleList() {
        List<Role> roles = new ArrayList<>();
        try {
            String sql = "select * from role";
//        List params = new ArrayList();
            resultSet = query(sql, null);
            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));
                roles.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return roles;
    }
}
