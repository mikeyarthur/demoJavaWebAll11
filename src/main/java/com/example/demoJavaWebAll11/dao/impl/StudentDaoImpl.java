package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.StudentDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends DBUtils implements StudentDao {
    /**
     * 获取学员的信息列表
     */
    @Override
    public List<Student> getStudents() {
        // 为什么这里list不用null，而是直接new 对象？方便后面数据处理，不用判断null，而是直接取数据吗？
        List list = new ArrayList<Student>();
        try {
            String sql = "select * from student";
            resultSet = query(sql, null);
            while (resultSet.next()) {
                Student student = new Student();
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuName(resultSet.getString("stuname"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setRegistered(resultSet.getString("registered"));
                student.setAddress(resultSet.getString("address"));
                student.setProfession(resultSet.getString("profession"));
                student.setIdNumber(resultSet.getString("idnumber"));
                student.setPolitics(resultSet.getString("politics"));
                student.setRegDate(resultSet.getDate("regdate"));
                student.setState(resultSet.getInt("state"));
                student.setIntroduction(resultSet.getString("introduction"));
                student.setGid(resultSet.getInt("gid"));
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return list;
    }
}
