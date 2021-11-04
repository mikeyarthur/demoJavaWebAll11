package com.example.demoJavaWebAll11.service.impl;

import com.example.demoJavaWebAll11.bean.Grade;
import com.example.demoJavaWebAll11.dao.GradeDao;
import com.example.demoJavaWebAll11.dao.impl.GradeDaoImpl;
import com.example.demoJavaWebAll11.service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {

    private GradeDao dao = new GradeDaoImpl();
    /**
     * 查询年级列表
     *
     * @return
     */
    @Override
    public List<Grade> getList() {
        return dao.getList();
    }
}
