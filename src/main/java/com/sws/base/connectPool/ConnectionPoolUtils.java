package com.sws.base.connectPool;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


public class ConnectionPoolUtils {


    private ConnectionPoolUtils() {
    }

    private static ConnectionPool poolInstance = null;

    public static ConnectionPool GetPoolInstance() {
        if (poolInstance == null) {
            try {
                poolInstance = new ConnectionPool("com.mysql.cj.jdbc.Driver",
                        "jdbc:mysql://39.96.74.32:25412/hssws?allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false",
                        "root",
                        "ASDzxc1993.");
                poolInstance.createPool();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return poolInstance;
    }
}