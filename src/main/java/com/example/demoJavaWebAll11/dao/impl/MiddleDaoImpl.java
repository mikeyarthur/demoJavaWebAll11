package com.example.demoJavaWebAll11.dao.impl;

import com.example.demoJavaWebAll11.dao.DBUtils;
import com.example.demoJavaWebAll11.dao.MiddleDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MiddleDaoImpl extends DBUtils implements MiddleDao {
    /**
     * @param roleid 中间表的roleid
     * @param ids    中间表的roleid对应的menuid列表
     * @return 数据库update影响的条数
     */
    @Override
    public int insertMiddle(int roleid, String[] ids) {
        int ok = 0;
        try {
            String sql = "insert into middle values (null, ?, ?)";
            // 批量新增
            pps = getPps(sql);
            for (String id : ids) {
                pps.setInt(1, roleid);
                pps.setString(2, id);
                pps.addBatch();
            }
            pps.executeBatch();

            // 不出异常，就是middle数据表新增操作执行ok
            ok = 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return ok;
    }

    /**
     * @param roleid
     * @return 根据传入的roleid，删除middle表中roleid对应的记录条数
     */
    @Override
    public int delete(int roleid) {
        int update = 0;
        try {
            String sql = "DELETE FROM middle WHERE roleid=?";
            List params = new ArrayList();
            params.add(roleid);

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return update;
    }
}
