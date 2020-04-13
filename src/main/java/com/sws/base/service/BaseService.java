//package com.sws.base.service;
//
//import com.sws.base.Entity.PageUtil;
//
//import java.util.List;
//
//public interface BaseService {
//
//    /**
//     * 查询所有列表
//     */
//    <T> List<T> findAllByPage(int page, int limit, String sort, int type);
//
//    /**
//     * 根据条件分页查询
//     */
//    <T> List<T> findAllByPage(Object obj,Class<T> clazz, int page, int limit,boolean vague);
//
//    /**
//     * 根据条件分页查询
//     */
//    <T> List<T> findAllByPageOr(Object obj,Class<T> clazz, int page, int limit,boolean vague);
//
//    /**
//     * 根据id查询某条数据
//     */
//    <T> T findById(Integer id,Class<T> clazz);
//
//    /**
//     * 和查询个数
//     */
//    int count(Object t,boolean vague);
//
//    /**
//     * 或查询个数
//     */
//    int orCount(Object t,boolean vague);
//
//    /**
//     * 查询全部数据
//     */
//    <T> List<T> findALL(Object obj,Class<T> clazz);
//
//    /**
//     * 根据条件查询
//     */
//    <T> List<T> findListByCondition(Object obj,Class<T> clazz,boolean vague);
//
//    /**
//     * 根据不等条件查询
//     */
//    <T> List<T> findByNoEqual(Object obj,Class<T> clazz,boolean vague);
//
//    /**
//     * 根据id更新
//     */
//    boolean updateListById(Integer id,Object t);
//
//    /**
//     * 根据条件更新
//     * return 成功更新个数
//     */
//    int updateListByCondition(Object k,Object t);
//
//    /**
//     * 新增数据
//     */
//    boolean save(Object t);
//
//    /**
//     * 根据id删除
//     */
//    boolean deleteById(String id);
//
//    /**
//     * 根据条件批量删除
//     */
//    boolean delete(Object t);
//}
