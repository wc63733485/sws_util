package com.sws.base.dao;

import com.sws.base.util.JavaBeanUtil;
import com.sws.base.util.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * 查询信息
     *
     * @param obj
     * @throws SQLException
     */
    public void delete(Object obj) throws SQLException {
        SqlUtil sqlUtil = new SqlUtil();
        String sql = sqlUtil.BaseDelete(obj);
        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 查询信息
     *
     * @param obj
     * @throws SQLException
     */
    public List<Object> queryByPage(Object obj, int page, int limit,boolean vague) {
        List<Object> array = new ArrayList<Object>();
        SqlUtil sqlUtil = new SqlUtil();
        String sql = sqlUtil.BaseQuery(obj, (page-1)*limit, limit,vague);

        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = state.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            HashMap map = null;
            while (resultSet.next()) {
                map = new HashMap();
                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    map.put(columnName, value);
                }
                Object o = JavaBeanUtil.mapToObject(map, obj.getClass());
                array.add(o);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }


    /**
     * 查询数量
     *
     * @param obj
     * @throws SQLException
     */
    public int count(Object obj,boolean vague) {
        List<Object> array = new ArrayList<Object>();
        SqlUtil sqlUtil = new SqlUtil();
        String sql = sqlUtil.Count(obj,vague);

        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * 查询信息
     *
     * @param obj
     * @throws SQLException
     */
    public List<Object> queryByCondition(Object obj, boolean vague) {
        List<Object> array = new ArrayList<Object>();
        SqlUtil sqlUtil = new SqlUtil();
        String sql = sqlUtil.BaseQueryNoPage(obj,vague);

        System.out.println(sql);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //com.mysql.jdbc.Driver已经弃用了，要加上cj
            Connection con = DriverManager.getConnection("jdbc:mysql://39.96.74.32:3306/hssws?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "ASDzxc1993.");
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = state.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            HashMap map = null;
            while (resultSet.next()) {
                map = new HashMap();
                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    map.put(columnName, value);
                }
                Object o = JavaBeanUtil.mapToObject(map, obj.getClass());
                array.add(o);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }

}
