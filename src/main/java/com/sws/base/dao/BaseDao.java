package com.sws.browser.dao;

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

    private static final SqlUtil sqlUtil = new SqlUtil();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public <T> List<T> findListByCondition(Object obj,Class<T> clazz, boolean b,String sort,int i) {
        String sql = sqlUtil.BaseQueryNoPage(obj,true,sort,i);
        return this.getResult(clazz,sql);
    }

    public boolean save(Object obj) {
        String sql = sqlUtil.BaseInsert(obj);
        return jdbcTemplate.update(sql)>0;
    }

    public boolean delete(Object obj) {
        String sql = sqlUtil.BaseDelete(obj);
        return jdbcTemplate.update(sql)>0;
    }

    public <T> List<T> queryByPage(Object obj, Class<T> clazz, int page, int limit, boolean vague,String sort,int i) {
        String sql = sqlUtil.BaseQuery(obj, (page - 1) * limit, limit, vague,sort,i);
        return this.getResult(clazz,sql);
    }

    public <T> List<T> queryByPageOr(Object obj, Class<T> clazz, int page, int limit, boolean vague,String sort,int i) {
        String sql = sqlUtil.BaseQueryOr(obj, (page - 1) * limit, limit, vague,sort,i);
        return this.getResult(clazz,sql);
    }

    public <T> List<T> queryByNoEqual(Object obj, Class<T> clazz, boolean vague,String sort,int i) {
        String sql = sqlUtil.BaseQueryNoEqualNoPage(obj, vague,sort,i);
        return this.getResult(clazz,sql);
    }

    public int andCount(Object obj, boolean vague) {
        String sql = sqlUtil.AndCount(obj, vague);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    public int orCount(Object obj, boolean vague) {
        String sql = sqlUtil.OrCount(obj, vague);
        return jdbcTemplate.queryForObject(sql, Integer.TYPE);
    }

    public <T> List<T> queryByCondition(Object obj, Class<T> clazz, boolean vague,String sort,int i) {
        String sql = sqlUtil.BaseQueryNoPage(obj, vague,sort,i);
        return this.getResult(clazz,sql);
    }

    public <T> List<T> queryByIn(Object obj,ArrayList arrayList, Class<T> clazz) {
        String sql = sqlUtil.BaseQueryIn(obj,arrayList);
        return this.getResult(clazz,sql);
    }

    public <T> List<T> queryAll(Object obj, Class<T> clazz,String sort,int i) {

        String sql = sqlUtil.BaseQueryAll(obj,sort,i);
        return this.getResult(clazz,sql);
    }

    public <T> List<T> getResult(Class<T> clazz,String sql){
        List<T> entities = new ArrayList<>();
        for (Map<String, Object> map:jdbcTemplate.queryForList(sql)) {
            T t = (T) JavaBeanUtil.mapToObject(map, clazz);
            entities.add(t);
        }
        return entities;
    }

    public int update(Object k, Object t) {
        String sql = sqlUtil.Update(k, t);
        return jdbcTemplate.update(sql);
    }


    public <T> T queryById(Integer id, Class<T> clazz) {
        String tn = clazz.getAnnotation(Entity.class).value();;
        String sql = sqlUtil.BaseIdQuery(tn, id);
        return JavaBeanUtil.mapToObject(jdbcTemplate.queryForMap(sql),clazz);
    }

    public <T> T queryOne(Object obj, Class<T> clazz,String sort,int i) {
        String sql = sqlUtil.BaseQueryNoPage(obj, false,sort,i);
        return JavaBeanUtil.mapToObject(jdbcTemplate.queryForMap(sql),clazz);
    }

    public <T> T queryOne(Object obj, Class<T> clazz) {
        String sql = sqlUtil.BaseQuery(obj);
        return JavaBeanUtil.mapToObject(jdbcTemplate.queryForMap(sql),clazz);
    }
}
