package com.example.demoJavaWebAll11.service;

import com.example.demoJavaWebAll11.bean.Student;

import java.util.List;

public interface StudentService {
    /**
     *
     * 获取学员的信息列表
     *
     * @param stuname 学生名字
     * @param stuno   学生编号
     * @param sex     性别
     * @param pageIndex 页码值
     * @param pageSize  每页显示条数
     * @return        模糊查询的学生列表
     */
    public List<Student> getStudents(String stuname, String stuno, int sex, int pageIndex, int pageSize);

    /**
     * 获得总条数（基于模糊查询）
     * @param stuname 学生名字
     * @param stuno   学生编号
     * @param sex     性别
     * @return        模糊查询的总条数
     */
    public int total(String stuname, String stuno, int sex);

    /**
     * 新增学生
     * @param student   学生对象（包含新增的所有信息）
     * @return          数据库影响条数
     */
    public int insertStu(Student student);

    /**
     *
     * @param sid  学生id
     * @return    主键查询的学生对象
     */
    public Student findById(int sid);

    /**
     *
     * @param student  修改学生信息的学生对象
     * @return          影响数据库的条数
     */
    public int updateStu(Student student);
}
