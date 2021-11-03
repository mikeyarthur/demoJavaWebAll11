package com.example.demoJavaWebAll11.service;

import com.example.demoJavaWebAll11.bean.Student;

import java.util.List;

public interface StudentService {
    /**
     * 获取学员的信息列表
     */
    public List<Student> getStudents();
}
