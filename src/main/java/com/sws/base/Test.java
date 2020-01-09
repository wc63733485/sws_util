package com.sws.base;

import com.sws.base.Entity.PumpHouse;
import com.sws.base.dao.BaseDao;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        PumpHouse be = new PumpHouse();
        be.setAddress("123");
        be.setCreateTime("123");
        be.setCreateUserId(123);
        be.setUpdateTime("12344");
        be.setUpdateUserId(123123);

        BaseDao bd = new BaseDao();
        try {
            bd.save(be);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
