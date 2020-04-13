//package com.sws.base.service.impl;
//
//import com.sws.base.dao.BaseDao;
//import com.sws.base.service.BaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class BaseServiceImpl implements BaseService {
//
//    @Autowired
//    private BaseDao bd = new BaseDao();
//
//    public List<Object> findAllByPage(int page, int limit, String sort, int type) {
//        return null;
//    }
//
//    public <T> List<T> findAllByPage(Object obj,Class<T> clazz, int page, int limit,boolean vague) {
//        return bd.queryByPage(obj,clazz, page, limit, vague);
//    }
//
//    public <T> List<T> findAllByPageOr(Object obj,Class<T> clazz, int page, int limit,boolean vague) {
//        return bd.queryByPageOr(obj,clazz, page, limit, vague);
//    }
//
//    public int count(Object t,boolean vague) {
//        return bd.count(t, vague);
//    }
//
//
//    public int orCount(Object t,boolean vague) {
//        return bd.countByPageOrCount(t, vague);
//    }
//
//    public <T> T findById(Integer id,Class<T> clazz) {
//        return bd.queryById(id, clazz);
//    }
//
//
//    public <T> List<T> findALL(Object obj,Class<T> clazz) {
//        return bd.queryAll(obj,clazz);
//    }
//
//    public <T> List<T> findByNoEqual(Object obj,Class<T> clazz, boolean vague) {
//        return bd.queryByNoEqual(obj,clazz,vague);
//    }
//
//    public <T> List<T> findListByCondition(Object obj,Class<T> clazz, boolean vague) {
//        return bd.queryByCondition(obj,clazz,vague);
//    }
//
//    public boolean updateListById(Integer id, Object obj) {
//        return false;
//    }
//
//    public int updateListByCondition(Object k, Object t) {
//        return bd.update( k, t);
//    }
//
//    public boolean save(Object t) {
//        return bd.save(t);
//    }
//
//    public boolean deleteById(String id) {
//        return false;
//    }
//
//    public boolean delete(Object t) {
//        return bd.delete(t);
//    }
//}
