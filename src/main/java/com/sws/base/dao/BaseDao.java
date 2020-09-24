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


    @Autowired
    public JdbcTemplate jdbcTemplate;

    public boolean save(Object obj) {
        String sql = SqlUtil.insert(obj);
        return jdbcTemplate.update(sql) > 0;
    }

    public boolean delete(Object obj) {
        String sql = SqlUtil.delete(obj);
        return jdbcTemplate.update(sql) > 0;
    }

    public <T> List<T> query(Object obj, Class<T> clazz, boolean vague, int page, int limit, String sort, int i) {
        String sql = SqlUtil.queryPageSort(obj, vague, (page - 1) * limit, limit, sort, i);
        return this.getResult(clazz, sql);
    }

//    public <T> List<T> queryByPageOr(Object obj, Class<T> clazz, int page, int limit, boolean vague,String sort,int i) {
//        String sql = SqlUtil.BaseQueryOr(obj, (page - 1) * limit, limit, vague,sort,i);
//        return this.getResult(clazz,sql);
//    }
//
//    public <T> List<T> queryByNoEqual(Object obj, Class<T> clazz, boolean vague,String sort,int i) {
//        String sql = SqlUtil.BaseQueryNoEqualNoPage(obj, vague,sort,i);
//        return this.getResult(clazz,sql);
//    }
//
//    public int andCount(Object obj, boolean vague) {
//        String sql = SqlUtil.AndCount(obj, vague);
//        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
//    }
//
//    public int orCount(Object obj, boolean vague) {
//        String sql = SqlUtil.OrCount(obj, vague);
//        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
//    }
//
//    public <T> List<T> queryByCondition(Object obj, Class<T> clazz, boolean vague,String sort,int i) {
//        String sql = SqlUtil.BaseQueryNoPage(obj, vague,sort,i);
//        return this.getResult(clazz,sql);
//    }
//
//    public <T> List<T> queryByIn(Object obj,ArrayList arrayList, Class<T> clazz) {
//        String sql = SqlUtil.BaseQueryIn(obj,arrayList);
//        return this.getResult(clazz,sql);
//    }
//
//    public <T> List<T> queryAll(Object obj, Class<T> clazz,String sort,int i) {
//        String sql = SqlUtil.BaseQueryAll(obj,sort,i);
//        return this.getResult(clazz,sql);
//    }

    public <T> List<T> queryIn(String tableName, Class<T> clazz, String queryField, List<Object> list) {
        String sql = SqlUtil.queryIn(tableName,queryField, list);
        return this.getResult(clazz, sql);
    }


    public <T> List<T> getResult(Class<T> clazz, String sql) {
        List<T> entities = new ArrayList<>();
        for (Map<String, Object> map : jdbcTemplate.queryForList(sql)) {
            T t = (T) JavaBeanUtil.mapToObject(map, clazz);
            entities.add(t);
        }
        return entities;
    }

    public int update(Object k, Object t) {
        String sql = SqlUtil.update(k, t);
        return jdbcTemplate.update(sql);
    }


    public <T> T queryById(Integer id, Class<T> clazz) {
        String tn = clazz.getAnnotation(Entity.class).value();
        String sql = SqlUtil.queryById(tn, id);
        return JavaBeanUtil.mapToObject(jdbcTemplate.queryForList(sql).get(0), clazz);
    }

    public <T> T queryOne(Object obj, Class<T> clazz) {
        String sql = SqlUtil.queryPage(obj, false, 0, 1);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(sql);
        if (maps.size() == 1) {
            System.out.println(maps.get(0).toString());
            return JavaBeanUtil.mapToObject(maps.get(0), clazz);
        }
        return null;
    }

    public <T> T queryOne(Object obj, Class<T> clazz, String sort, int i) {
        String sql = SqlUtil.queryPageSort(obj, false, 0, 1, sort, i);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        if (maps.size() == 1) {
            return JavaBeanUtil.mapToObject(maps.get(0), clazz);
        }
        return null;
    }
}
