package com.sws.base.dao;

import com.sws.base.annotations.Entity;
import com.sws.base.connectPool.ConnectionPool;
import com.sws.base.connectPool.ConnectionPoolUtils;
import com.sws.base.util.JavaBeanUtil;
import com.sws.base.util.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseDao {

    private static ConnectionPool connPool = ConnectionPoolUtils.GetPoolInstance();//单例模式创建连接池对象
    private static final SqlUtil sqlUtil = new SqlUtil();

    public ConnectionPool getConnectionPool(){
        return this.connPool;
    }
    /**
     * 保存信息
     *
     * @param obj
     * @throws SQLException
     */
    public boolean save(Object obj) {
        String sql = sqlUtil.BaseInsert(obj);
        System.out.println(sql);
        boolean res = false;
        try {
            Connection conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
            PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
            res = state.execute();
            state.close();
            connPool.returnConnection(conn);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     * 删除信息
     *
     * @param obj
     * @throws SQLException
     */
    public boolean delete(Object obj) {
        String sql = sqlUtil.BaseDelete(obj);
        System.out.println(sql);
        boolean res = false;
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            res = preparedStatement.execute();
            preparedStatement.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     * 分页查询
     *
     * @param obj
     * @throws SQLException
     */
    public <T> List<T>  queryByPage(Object obj,Class<T> clazz, int page, int limit,boolean vague) {
        List<T> array = new ArrayList<T>();
        String sql = sqlUtil.BaseQuery(obj, (page-1)*limit, limit,vague);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                T t = (T) JavaBeanUtil.mapToObject(map,clazz);
                array.add(t);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }


    /**
     * 分页查询
     *
     * @param obj
     * @throws SQLException
     */
    public <T> List<T> queryByPageOr(Object obj,Class<T> clazz, int page, int limit,boolean vague) {
        List<T> array = new ArrayList<T>();
        String sql = sqlUtil.BaseQueryOr(obj, (page-1)*limit, limit,vague);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                T t = (T) JavaBeanUtil.mapToObject(map,clazz);
                array.add(t);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }


    /**
     * 分页查询
     *
     * @param obj
     * @throws SQLException
     */
    public <T> List<T> queryByNoEqual(Object obj,Class<T> clazz,boolean vague) {
        List<T> array = new ArrayList<T>();
        String sql = sqlUtil.BaseQueryNoEqualNoPage(obj,vague);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                T t = (T) JavaBeanUtil.mapToObject(map,clazz);
                array.add(t);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
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
        String sql = sqlUtil.Count(obj,vague);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * 条件查询信息
     *
     * @param obj
     * @throws SQLException
     */
    public  <T> List<T>  queryByCondition(Object obj,Class<T> clazz, boolean vague) {
        List<T> array = new ArrayList<T>();
        String sql = sqlUtil.BaseQueryNoPage(obj,vague);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                T t = (T) JavaBeanUtil.mapToObject(map,clazz);
                array.add(t);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }


    /**
     * 查询全部信息
     *
     * @param obj
     * @throws SQLException
     */
    public <T> List<T>  queryAll(Object obj,Class<T> clazz){
        List<T> array = new ArrayList<T>();
        String sql = sqlUtil.BaseQueryAll(obj);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                T t = (T) JavaBeanUtil.mapToObject(map,clazz);
                array.add(t);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return array;
    }

    /**
     * 更新信息
     *
     * @param k j
     * @throws SQLException
     */
    public int update(Object k, Object t) {
        List<Object> array = new ArrayList<Object>();
        String sql = sqlUtil.Update(k, t);
        int i = 0;
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
            PreparedStatement state = (PreparedStatement) con.prepareStatement(sql);
            i = state.executeUpdate(sql);
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public int countByPageOrCount(Object obj, boolean vague) {
        List<Object> array = new ArrayList();
        String sql = sqlUtil.BaseQueryOrCount(obj , vague);
        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet resultSet = state.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception var9) {
            System.out.println(var9);
        }
        return 0;
    }


    /**
     * id查询信息
     *
     * @throws SQLException
     */
    public <T> T queryById(Integer id,Class<T> clazz) {
        T t = null;
        Entity annotation = clazz.getAnnotation(Entity.class);
        String tn = annotation.value();
        String sql = sqlUtil.BaseIdQuery(tn,id);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                t = (T) JavaBeanUtil.mapToObject(map,clazz);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return t;
    }

    /**
     * 条件查询 返回单个数据
     *
     * @throws SQLException
     */
    public <T> T queryOne(Object obj,Class<T> clazz) {
        T t = null;
        Entity annotation = clazz.getAnnotation(Entity.class);
        String tn = annotation.value();
        String sql = sqlUtil.BaseQueryNoPage(obj,false);
//        System.out.println(sql);
        try {
            Connection con = connPool.getConnection(); // 从连接库中获取一个可用的连接
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
                    map.put(columnName, resultSet.getObject(columnName));
                }
                t = (T) JavaBeanUtil.mapToObject(map,clazz);
            }
            resultSet.close();
            state.close();
            connPool.returnConnection(con);// 连接使用完后释放连接到连接池
        } catch (Exception e) {
            System.out.println(e);
        }
        return t;
    }
}
