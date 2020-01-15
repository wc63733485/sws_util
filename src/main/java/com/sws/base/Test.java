package com.sws.base;

import com.mysql.cj.util.LogUtils;
import com.sws.base.Entity.PumpHouse;
import com.sws.base.dao.BaseDao;
import com.sws.base.util.LogMessage;
import com.sws.base.util.YamlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(LogMessage.getLogLevel());
    }

}
