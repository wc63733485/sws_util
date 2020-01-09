package com.sws.base.dao;

import com.sws.base.util.FieldUtil;
import com.sws.base.util.TableUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDao {

    /**
     * 插入模板
     */
    private static final String INSERT_FORMAT = "INSERT INTO %s ( %s ) VALUES ( %s ) ;";

    /**
     * 保存用户信息
     *
     * @param obj
     * @throws SQLException
     */
    public void save(Object obj) throws SQLException {
        String sql = BaseInsert(obj);
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
//     * 构建插入语句
//     *
//     * @param obj
//     * @return
//     */
//    public String buildInsertSQL(Object obj) {
//        String tableName = TableUtil.getTableName(obj);
//        String fieldString = FieldUtil.getFieldNameString(obj);
//        String valueString = FieldUtil.getFieldValueString(obj);
//
//        return String.format(INSERT_FORMAT, tableName, fieldString, valueString);
//    }

    /**
     * 构建插入语句
     *
     * @param obj
     * @return
     */
    public String BaseInsert(Object obj) {
        String tableName = TableUtil.getTableName(obj);
        String fieldString = FieldUtil.getFieldNameString(obj);
        String valueString = FieldUtil.getFieldValueString(obj);

        return String.format(INSERT_FORMAT, tableName, fieldString, valueString);
    }
}
