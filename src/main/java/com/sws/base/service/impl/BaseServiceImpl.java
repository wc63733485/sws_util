package com.sws.base.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sws.base.dao.BaseDao;
import com.sws.base.service.BaseService;

import java.sql.SQLException;
import java.util.List;

public class BaseServiceImpl implements BaseService {

    public List<JSONObject> findAllByPage(int page, int limit, String sort, int type) {
        return null;
    }

    public List<JSONObject> findAllByPage(Object t, int page, int limit, String sort, int type) {
        BaseDao bd = new BaseDao();
        try {
            List<JSONObject> array = bd.queryByPage(t, page, limit);
            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
