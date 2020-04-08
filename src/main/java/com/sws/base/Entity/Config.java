//package com.sws.base.Entity;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Config {
//
//    private static String password;
//    private static String username;
//    private static String url;
//    private static String driverClassName;
//
//    @Value("${mysql.username}")
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    @Value("${mysql.password}")
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    @Value("${mysql.url}")
//    public void setUrl(String url) {
//        this.url = url;
//    }
//    @Value("${mysql.driverClassName}")
//    public void setDriverClassName(String driverClassName) {
//        this.driverClassName = driverClassName;
//    }
//
//    public String getPassword() {
//        System.out.println(this.password);
//        return password;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public String getDriverClassName() {
//        return driverClassName;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//}
