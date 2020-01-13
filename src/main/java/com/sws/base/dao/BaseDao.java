package com.sws.base.dao;

import com.sws.base.util.SqlUtil;

import java.sql.*;

public class BaseDao {

    /**
     * 保存信息
     *
     * @param obj
     * @throws SQLException
     */
    public void save(Object obj) throws SQLException {
        SqlUtil sqlUtil = new SqlUtil();
        String sql = sqlUtil.BaseInsert(obj);
        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            state.execute();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    /**
//     * 查询信息
//     *
//     * @param obj
//     * @throws SQLException
//     */
//    public void query(Object obj) throws SQLException {
//        SqlUtil sqlUtil = new SqlUtil();
//        String sql = sqlUtil.BaseQuery(obj);
//        System.out.println(sql);
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
//            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
//            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
//            state.execute();
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    /**
     * 查询信息
     *
     * @param obj
     * @throws SQLException
     */
    public void queryByPage(Object obj, int page, int limit) throws SQLException {
        SqlUtil sqlUtil = new SqlUtil();
        String sql = null;

        sql = sqlUtil.BaseQuery(obj, 0, 5);

        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = state.executeQuery();
            resultSet.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
