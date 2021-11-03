package com.example.demoJavaWebAll11.dao.impl;

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
}
