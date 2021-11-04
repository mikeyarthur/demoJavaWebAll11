package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Student;
import com.example.demoJavaWebAll11.dao.StudentDao;
import com.example.demoJavaWebAll11.dao.impl.StudentDaoImpl;
import com.example.demoJavaWebAll11.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao dao = new StudentDaoImpl();

    /**
     * 获取学员的信息列表
     *
     * @param stuname   学生名字
     * @param stuno     学生编号
     * @param sex       性别
     * @param pageIndex 页码值
     * @param pageSize  每页显示条数
     */
    @Override
    public List<Student> getStudents(String stuname, String stuno, int sex, int pageIndex, int pageSize) {
        return dao.getStudents(stuname, stuno, sex, pageIndex, pageSize);
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
        return dao.total(stuname, stuno, sex);
    }

    /**
     * 新增学生
     *
     * @param student 学生对象（包含新增的所有信息）
     * @return 数据库影响条数
     */
    @Override
    public int insertStu(Student student) {
        return dao.insertStu(student);
    }

    /**
     * @param sid 学生id
     * @return 主键查询的学生对象
     */
    @Override
    public Student findById(int sid) {
        return dao.findById(sid);
    }

    /**
     * @param student 修改学生信息的学生对象
     * @return 影响数据库的条数
     */
    @Override
    public int updateStu(Student student) {
        return dao.updateStu(student);
    }

    /**
     * @param sid 学生id
     * @return 数据库影响的条数
     */
    @Override
    public int deleteById(int sid) {
        return dao.deleteById(sid);
    }
}
