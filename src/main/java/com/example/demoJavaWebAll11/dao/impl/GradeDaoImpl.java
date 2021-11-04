package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Grade;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.GradeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends DBUtils implements GradeDao {
    /**
     * 查询年级列表
     *
     * @return
     */
    @Override
    public List<Grade> getList() {
        List<Grade> list = new ArrayList<Grade>();
        try {
            String sql = "select * from grade";
            resultSet = query(sql, null);
//            if (resultSet == null) {
//                return list;
//            }
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setGradeId(resultSet.getInt("gradeid"));
                grade.setGradeName(resultSet.getString("gradename"));
                list.add(grade);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return list;
    }
}
