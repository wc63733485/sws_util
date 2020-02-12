package com.sws.base.service.impl;

import com.sws.base.Entity.PageUtil;
import com.sws.base.dao.BaseDao;
import com.sws.base.service.BaseService;
import java.util.List;

public class BaseServiceImpl implements BaseService {

    public List<Object> findAllByPage(int page, int limit, String sort, int type) {
        return null;
    }

    public PageUtil findAllByPage(Object t, int page, int limit,boolean vague) {
        return new PageUtil(t, page, limit, vague);
    }

    public PageUtil findAllByPage(Object t, int page, int limit, String sort, int type) {
        return null;
    }

    public int count(Object t,boolean vague) {
        BaseDao bd = new BaseDao();
        return bd.count(t, vague);
    }

    public Object findById(String id) {
        return null;
    }

    public List<?> findListByCondition(Object t, boolean vague) {
        BaseDao bd = new BaseDao();
        return bd.queryByCondition(t,vague);
    }

    public boolean updateListById(String id, Object t) {
        return false;
    }

    public boolean save(Object t) {
        return false;
    }

    public boolean deleteById(String id) {
        return false;
    }

    public boolean deleteById(Object t) {
        return false;
    }
}
