package com.sws.base.connectPool;

import com.alibaba.fastjson.JSONObject;
import com.sws.base.util.InitializeConfig;



public class ConnectionPoolUtils {

    private static JSONObject config = InitializeConfig.getConfig();


    private ConnectionPoolUtils() {
    }

    private static ConnectionPool poolInstance = null;

    public static ConnectionPool GetPoolInstance() {
        if (poolInstance == null) {
            try {
                poolInstance = new ConnectionPool(config.getJSONObject("mysql").getString("driverClassName"),
                        config.getJSONObject("mysql").getString("url"),
                        config.getJSONObject("mysql").getString("username"),
                        config.getJSONObject("mysql").getString("password"));
                poolInstance.createPool();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return poolInstance;
    }
}