package com.sws.base.service;

import com.sws.base.Entity.PageUtil;

import java.util.List;

public interface BaseService {

    /**
     * 查询所有列表
     */
    List<Object> findAllByPage(int page, int limit, String sort, int type);

    /**
     * 根据条件分页查询
     */
    PageUtil findAllByPage(Object t, int page, int limit,boolean vague);

    /**
     * 根据条件分页查询
     */
    PageUtil findAllByPage(Object t,int page,int limit,String sort,int type);

    /**
     * 根据id查询某条数据
     */
    Object findById(String id);

    /**
     * 根据条件查询
     */
    List<?> findListByCondition(Object t,boolean vague);

    /**
     * 根据id更新
     */
    boolean updateListById(String id,Object t);

    /**
     * 新增数据
     */
    boolean save(Object t);

    /**
     * 根据id删除
     */
    boolean deleteById(String id);

    /**
     * 根据条件批量删除
     */
    boolean deleteById(Object t);
}
