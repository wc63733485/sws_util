package com.sws.base;

import com.sws.base.Entity.PumpHouse;
import com.sws.base.dao.BaseDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws Exception {
        PumpHouse be = new PumpHouse();
        be.setUpdateUserId(23);
        be.setAddress("1243");
        be.setCreateTime("123");

        BaseDao bd = new BaseDao();
        try {
            bd.save(be);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
