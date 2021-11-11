package com.example.demoJavaWebAll11.dao;

public interface MiddleDao {
    /**
     *
     * @param roleid  中间表的roleid
     * @param ids     中间表的roleid对应的menuid列表
     * @return        数据库update影响的条数
     */
    public int insertMiddle(int roleid, String[] ids);

    /**
     *
     * @param roleid
     * @return       根据传入的roleid，删除middle表中roleid对应的记录条数
     */
    public int delete(int roleid);
}
