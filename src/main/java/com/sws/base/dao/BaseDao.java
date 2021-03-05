package com.sws.base.dao;

import com.sws.base.annotations.Entity;
import com.sws.base.util.JavaBeanUtil;
import com.sws.base.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BaseDao {


    @Autowired
    public JdbcTemplate jdbcTemplate;

    public <T> List<T> queryCondition(Class<T> clazz,Object obj,boolean vague, String queryField, List<Integer> list,String sort, int i, int page, int limit) {
        String sql = SqlUtil.queryCondition(obj,vague, queryField, list,sort,i,(page - 1) * limit, limit);
        System.out.println(sql);
        return this.getResult(clazz, sql);
    }

    public int countCondition(Object obj,boolean vague, String queryField, List<Integer> list) {
        String sql = SqlUtil.countCondition(obj,vague, queryField, list);
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    public boolean save(Object obj) {
        String sql = SqlUtil.insert(obj);
        return jdbcTemplate.update(sql) > 0;
    }

    public boolean delete(Object obj) {
        String sql = SqlUtil.delete(obj);
        return jdbcTemplate.update(sql) > 0;
    }

    public <T> List<T> query(Object obj, Class<T> clazz) {
        String sql = SqlUtil.query(obj);
        return this.getResult(clazz, sql);
    }

    public <T> List<T> querySortPage(Object obj, Class<T> clazz, boolean vague, String sort, int i, int page, int limit) {
        String sql = SqlUtil.querySortPage(obj, vague, sort, i, (page - 1) * limit, limit);
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

    public int count(Object obj, boolean vague) {
        String sql = SqlUtil.andCount(obj, vague);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }
    public <T> int countAll(Class<T> clazz) {
        String tn = clazz.getAnnotation(Entity.class).value();
        String sql = SqlUtil.countAll(tn);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    public int orCount(Object obj, boolean vague) {
        String sql = SqlUtil.orCount(obj, vague);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    public int inCountStr(Object obj,String queryField,  List<String> array) {
        String sql = SqlUtil.inCountStr(obj,queryField,array);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    public int inCountInt(Object obj,String queryField,  List<Integer> array) {
        String sql = SqlUtil.inCountInt(obj,queryField,array);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }
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
//    public <T> List<T> queryOr(Object obj, ArrayList arrayList, Class<T> clazz) {
//        String sql = SqlUtil.BaseQueryIn(obj, arrayList);
//        return this.getResult(clazz, sql);
//    }

    public <T> List<T> queryAll(Class<T> clazz) {
        String tn = clazz.getAnnotation(Entity.class).value();
        String sql = SqlUtil.queryAll(tn);
        return this.getResult(clazz, sql);
    }

    public <T> List<T> queryAllSort(Class<T> clazz,String sort, int i) {
        String tn = clazz.getAnnotation(Entity.class).value();
        String sql = SqlUtil.queryAllSort(tn,sort,i);
        return this.getResult(clazz, sql);
    }

    public <T> List<T> queryAllSortPage(Class<T> clazz, String sort, int i, int page, int limit) {
        String tn = clazz.getAnnotation(Entity.class).value();
        String sql = SqlUtil.queryAllSortPage(tn, sort, 1, (page - 1) * limit, limit);
        return this.getResult(clazz, sql);
    }

    public <T> List<T> queryIn(Class<T> clazz, String queryField, List<String> list) {
        String sql = SqlUtil.queryIn(clazz, queryField, list);
        return this.getResult(clazz, sql);
    }

    public <T> List<T> queryInByIntArray(Class<T> clazz, String queryField, List<Integer> list) {
        String sql = SqlUtil.queryInByIntArray(clazz, queryField, list);
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
        if (maps.size() == 1) {
            return JavaBeanUtil.mapToObject(maps.get(0), clazz);
        }
        return null;
    }

    public <T> T queryOne(Object obj, Class<T> clazz, String sort, int i) {
        String sql = SqlUtil.querySortPage(obj, false, sort, i, 0, 1);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        if (maps.size() == 1) {
            return JavaBeanUtil.mapToObject(maps.get(0), clazz);
        }
        return null;
    }

}
