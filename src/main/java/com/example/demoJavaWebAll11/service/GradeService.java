package com.example.demoJavaWebAll11.service;

import com.example.demoJavaWebAll11.bean.Grade;

import java.util.List;

public interface GradeService {
    /**
     * 查询年级列表
     * @return
     */
    public List<Grade> getList();
}
