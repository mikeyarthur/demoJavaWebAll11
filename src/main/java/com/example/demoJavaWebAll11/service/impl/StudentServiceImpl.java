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
     */
    @Override
    public List<Student> getStudents() {
        return dao.getStudents();
    }
}
