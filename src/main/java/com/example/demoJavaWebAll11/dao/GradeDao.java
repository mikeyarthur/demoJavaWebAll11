package com.example.demoJavaWebAll11.dao;

import com.example.demoJavaWebAll11.bean.Grade;

import java.util.List;

public interface GradeDao {
    /**
     * 查询年级列表
     * @return
     */
    public List<Grade> getList();
}
