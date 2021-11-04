package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.StudentDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            StringBuffer sql = new StringBuffer(" select * from student where 1=1 and state!=4 ");
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
            StringBuffer sql = new StringBuffer(" select count(*) from student where 1=1 and state!=4 ");
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

    /**
     * 新增学生
     *
     * @param student 学生对象（包含新增的所有信息）
     * @return 数据库影响条数
     */
    @Override
    public int insertStu(Student student) {
        int result = 0;
        try {
            // 第一个传null，自动递增
            String sql = "insert into student values (null, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            List params = new ArrayList();
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
//        params.add(student.getRegDate());
            params.add(new Date());
//        params.add(student.getState());
            params.add(1);  // 1 表示在读, 2 休学， 3 退学， 4 删除
            params.add(student.getIntroduction());
            params.add(student.getGid());

            result = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return result;
    }

    /**
     * @param sid 学生id
     * @return 主键查询的学生对象
     */
    @Override
    public Student findById(int sid) {
        Student student = null;
        try {
            String sql = "select * from student where stuid=? ";
            List params = new ArrayList();
            params.add(sid);

            resultSet = query(sql, params);
            while (resultSet.next()) {
                student = new Student();
                student.setStuId(resultSet.getInt("stuId"));
                student.setStuName(resultSet.getString("stuName"));
                student.setStuNo(resultSet.getString("stuNo"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setRegistered(resultSet.getString("registered"));
                student.setAddress(resultSet.getString("address"));
                student.setProfession(resultSet.getString("profession"));
                student.setIdNumber(resultSet.getString("idNumber"));
                student.setPolitics(resultSet.getString("politics"));
                student.setRegDate(resultSet.getDate("regDate"));
                student.setState(resultSet.getInt("state"));
                student.setIntroduction(resultSet.getString("introduction"));
                student.setGid(resultSet.getInt("gid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return student;
    }

    /**
     * @param student 修改学生信息的学生对象
     * @return 影响数据库的条数
     */
    @Override
    public int updateStu(Student student) {
        int update = 0;
        try {
            List params = new ArrayList();
//            String sql = "update student set stuname=?, address=?, sex=? where stuid=? ";
//            params.add(student.getStuName());
//            params.add(student.getAddress());
//            params.add(student.getSex());
//            params.add(student.getStuId());

            String sql = "update student set stuname=?, stuno=?, sex=?, phone=?, email=?, registered=?, address=?, profession=?, idnumber=?, politics=?, introduction=?, gid=? where stuid=? ";
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(student.getIntroduction());
            params.add(student.getGid());
            params.add(student.getStuId());

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return update;
    }

    /**
     * @param sid 学生id
     * @return 数据库影响的条数
     */
    @Override
    public int deleteById(int sid) {
        // 1. 假删除
        int update = 0;
        try {
            String sql = "update student set state=? where stuid=?";
            List params = new ArrayList();
            params.add(4);
            params.add(sid);
            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return update;
    }
}
