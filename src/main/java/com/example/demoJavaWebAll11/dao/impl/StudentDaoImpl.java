package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.StudentDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl extends DBUtils implements StudentDao {
    /**
     *
     * @param stuname 学生名字
     * @param stuno   学生编号
     * @param sex     性别
     * @param pageIndex 页码值
     * @param pageSize  每页显示条数
     * @return        模糊查询的学生列表
     */
    @Override
    public List<Student> getStudents(String stuname, String stuno, int sex, int pageIndex, int pageSize) {
        // 为什么这里list不用null，而是直接new 对象？方便后面数据处理，不用判断null，而是直接取数据吗？
        List list = new ArrayList<Student>();
        List params = new ArrayList();
        try {
//            String sql = "select * from student";
//            resultSet = query(sql, null);
            // 1. 模糊查找
            StringBuffer sql = new StringBuffer(" select * from student where 1=1 ");
            if (stuname != null && stuname.length() > 0) {
                sql.append(" and stuname like ? ");
                params.add("%" + stuname + "%");
            }
            if (stuno != null && stuno.length() > 0) {
                sql.append(" and stuno like ? ");
                params.add("%" + stuno + "%");
            }
            if (sex != -1) {
                sql.append(" and sex=? ");
                params.add(sex);
            }

            // 2. 分页
            sql.append(" limit ?,? ");
            // 目标：第1条到第5条   ==》limit 0, 5
            //      第6条到第10条  ==》limit 5，5
            //      第11条到第15条 ==》limit 10，5
            //                      limit        0,                       5
            //                      limit (pageIndex - 1) * pageSize, pageSize
            params.add((pageIndex - 1) * pageSize);
            params.add(pageSize);

            resultSet = query(sql.toString(), params);

            if (resultSet == null) {
                return list;
            }
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

    /**
     * 获得总条数（基于模糊查询）
     *
     * @param stuname 学生名字
     * @param stuno   学生编号
     * @param sex     性别
     * @return 模糊查询的总条数
     */
    @Override
    public int total(String stuname, String stuno, int sex) {
        int total = 0;
        List params = new ArrayList();
        try {
            // 1. 模糊查找
            StringBuffer sql = new StringBuffer(" select count(*) from student where 1=1 ");
            if (stuname != null && stuname.length() > 0) {
                sql.append(" and stuname like ? ");
                params.add("%" + stuname + "%");
            }
            if (stuno != null && stuno.length() > 0) {
                sql.append(" and stuno like ? ");
                params.add("%" + stuno + "%");
            }
            if (sex != -1) {
                sql.append(" and sex=? ");
                params.add(sex);
            }

            resultSet = query(sql.toString(), params);
            while (resultSet.next()) {
                // 直接获取第一列的数据，也就是，count(*)
                total = resultSet.getInt(1);
            }

//            // 2. 分页
//            sql.append(" limit ?,? ");
//            // 目标：第1条到第5条   ==》limit 0, 5
//            //      第6条到第10条  ==》limit 5，5
//            //      第11条到第15条 ==》limit 10，5
//            //                      limit        0,                       5
//            //                      limit (pageIndex - 1) * pageSize, pageSize
//            params.add((pageIndex - 1) * pageSize);
//            params.add(pageSize);
//
//            resultSet = query(sql.toString(), params);
//
//            if (resultSet == null) {
//                return list;
//            }
//            while (resultSet.next()) {
//                Student student = new Student();
//                student.setStuId(resultSet.getInt("stuid"));
//                student.setStuName(resultSet.getString("stuname"));
//                student.setStuNo(resultSet.getString("stuno"));
//                student.setSex(resultSet.getInt("sex"));
//                student.setPhone(resultSet.getString("phone"));
//                student.setEmail(resultSet.getString("email"));
//                student.setRegistered(resultSet.getString("registered"));
//                student.setAddress(resultSet.getString("address"));
//                student.setProfession(resultSet.getString("profession"));
//                student.setIdNumber(resultSet.getString("idnumber"));
//                student.setPolitics(resultSet.getString("politics"));
//                student.setRegDate(resultSet.getDate("regdate"));
//                student.setState(resultSet.getInt("state"));
//                student.setIntroduction(resultSet.getString("introduction"));
//                student.setGid(resultSet.getInt("gid"));
//                list.add(student);
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return total;
    }
}
