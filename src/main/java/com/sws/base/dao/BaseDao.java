package com.sws.base.dao;

import com.sws.base.annotations.Entity;
import com.sws.base.util.JavaBeanUtil;
import com.sws.base.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BaseDao {

    private static final SqlUtil sqlUtil = new SqlUtil();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(Object obj) {
        String sql = sqlUtil.insert(obj);
        return jdbcTemplate.update(sql) > 0;
    }

    public boolean delete(Object obj) {
        String sql = sqlUtil.delete(obj);
        return jdbcTemplate.update(sql) > 0;
    }

    public <T> List<T> query(Object obj, Class<T> clazz, int page, int limit, boolean vague, String sort, int i) {
        String sql = sqlUtil.queryPageSort(obj, vague, (page - 1) * limit, limit, sort, i);
        return this.getResult(clazz, sql);
    }

//    public <T> List<T> queryByPageOr(Object obj, Class<T> clazz, int page, int limit, boolean vague,String sort,int i) {
//        String sql = sqlUtil.BaseQueryOr(obj, (page - 1) * limit, limit, vague,sort,i);
//        return this.getResult(clazz,sql);
//    }
//
//    public <T> List<T> queryByNoEqual(Object obj, Class<T> clazz, boolean vague,String sort,int i) {
//        String sql = sqlUtil.BaseQueryNoEqualNoPage(obj, vague,sort,i);
//        return this.getResult(clazz,sql);
//    }
//
//    public int andCount(Object obj, boolean vague) {
//        String sql = sqlUtil.AndCount(obj, vague);
//        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
//    }
//
//    public int orCount(Object obj, boolean vague) {
//        String sql = sqlUtil.OrCount(obj, vague);
//        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
//    }
//
//    public <T> List<T> queryByCondition(Object obj, Class<T> clazz, boolean vague,String sort,int i) {
//        String sql = sqlUtil.BaseQueryNoPage(obj, vague,sort,i);
//        return this.getResult(clazz,sql);
//    }
//
//    public <T> List<T> queryByIn(Object obj,ArrayList arrayList, Class<T> clazz) {
//        String sql = sqlUtil.BaseQueryIn(obj,arrayList);
//        return this.getResult(clazz,sql);
//    }
//
//    public <T> List<T> queryAll(Object obj, Class<T> clazz,String sort,int i) {
//        String sql = sqlUtil.BaseQueryAll(obj,sort,i);
//        return this.getResult(clazz,sql);
//    }

    public <T> List<T> getResult(Class<T> clazz, String sql) {
        List<T> entities = new ArrayList<>();
        for (Map<String, Object> map : jdbcTemplate.queryForList(sql)) {
            T t = (T) JavaBeanUtil.mapToObject(map, clazz);
            entities.add(t);
        }
        return entities;
    }

    public int update(Object k, Object t) {
        String sql = sqlUtil.update(k, t);
        return jdbcTemplate.update(sql);
    }


    public <T> T queryById(Integer id, Class<T> clazz) {
        String tn = clazz.getAnnotation(Entity.class).value();
        ;
        String sql = sqlUtil.queryById(tn, id);
        return JavaBeanUtil.mapToObject(jdbcTemplate.queryForMap(sql), clazz);
    }

    public <T> T queryOne(Object obj, Class<T> clazz) {
        T t = (T) new Object();
        String sql = sqlUtil.queryPage(obj, false, 1, 1);
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql);
        if (stringObjectMap.size() > 0) {
            return (T)JavaBeanUtil.mapToObject(jdbcTemplate.queryForMap(sql), clazz);
        }
        return t;
    }
}
