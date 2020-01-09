package com.sws.base.service.impl;

import com.sws.base.service.BaseService;

import java.util.List;

public class BaseServiceImpl implements BaseService {

    public List<?> findAllByPage(int page, int limit, String sort, int type) {
        return null;
    }

    public List<?> findAllByPage(Object t, int page, int limit, String sort, int type) {
        return null;
    }

    public Object findById(String id) {
        return null;
    }

    public List<?> findListByCondition(Object t) {
        return null;
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
