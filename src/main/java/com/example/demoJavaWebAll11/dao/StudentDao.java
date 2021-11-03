package com.example.demoJavaWebAll11.dao;

import com.example.demoJavaWebAll11.bean.Student;

import java.util.List;

public interface StudentDao {

    /**
     * 获取学员的信息列表
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
}
