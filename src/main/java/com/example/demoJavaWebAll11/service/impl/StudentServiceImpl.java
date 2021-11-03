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
}
