package com.sws.base;

import com.sws.base.Entity.PumpHouse;
import com.sws.base.dao.BaseDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws Exception {
        PumpHouse be = new PumpHouse();
        be.setAddress("123");
        be.setCreateTime("123");

        BaseDao bd = new BaseDao();
        try {
            bd.queryByPage(be,0,2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
