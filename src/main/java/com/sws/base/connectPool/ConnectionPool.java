package com.sws.base.connectPool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool {

    private String jdbcDriver = ""; // 数据库驱动
    private String dbUrl = ""; // 数据 URL
    private String dbUsername = ""; // 数据库用户名
    private String dbPassword = ""; // 数据库用户密码

    private int initialConnections = 10; // 连接池的初始大小
    private int incrementalConnections = 5;// 连接池自动增加的大小
    private int maxConnections = 50; // 连接池最大的大小
    private CopyOnWriteArrayList connections = null; // 存放连接池中数据库连接的向量 , 初始时为 null

    public ConnectionPool(String jdbcDriver, String dbUrl, String dbUsername,
                          String dbPassword) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }


    /**
     *
     * 创建一个数据库连接池，连接池中的可用连接的数量采用类成员 initialConnections 中设置的值
     */
    public synchronized void createPool() throws Exception {
        if (connections != null) {
            return;
        }
        Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
        DriverManager.registerDriver(driver); // 注册 JDBC 驱动程序
        connections = new  CopyOnWriteArrayList();
        createConnections(this.initialConnections);
    }

    private void createConnections(int numConnections) throws SQLException {

        for (int x = 0; x < numConnections; x++) {
            if (this.maxConnections > 0 && this.connections.size() >= this.maxConnections) {
                break;
            }
            try {
                connections.add(new PooledConnection(newConnection()));
            } catch (SQLException e) {
                System.out.println(" 创建数据库连接失败！ "+x+"已存在" + e.getMessage());
                throw new SQLException();
            }

        }
    }
    /**
     * 创建一个新的数据库连接并返回它
     *
     * @return 返回一个新创建的数据库连接
     */
    private Connection newConnection() throws SQLException {
        // 创建一个数据库连接
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername,
                dbPassword);

        if (connections.size() == 0) {
            DatabaseMetaData metaData = conn.getMetaData();
            int driverMaxConnections = metaData.getMaxConnections();
            if (driverMaxConnections > 0 && this.maxConnections > driverMaxConnections) {
                this.maxConnections = driverMaxConnections;
            }
        }
        return conn; // 返回创建的新的数据库连接
    }


    public synchronized Connection getConnection() throws SQLException {
        // 确保连接池己被创建
        if (connections == null) {
            return null; // 连接池还没创建，则返回 null
        }
        Connection conn = getFreeConnection();
        while (conn == null) {

            wait(10);
            conn = getFreeConnection(); // 重新再试，直到获得可用的连接，如果
            // getFreeConnection() 返回的为 null
            // 则表明创建一批连接后也不可获得可用连接
        }
        return conn;// 返回获得的可用的连接
    }


    private Connection getFreeConnection() throws SQLException {
        // 从连接池中获得一个可用的数据库连接
        Connection conn = findFreeConnection();
        if (conn == null) {
            // 如果目前连接池中没有可用的连接
            // 创建一些连接
            createConnections(incrementalConnections);
            // 重新从池中查找是否有可用连接
            conn = findFreeConnection();
            if (conn == null) {
                // 如果创建连接后仍获得不到可用的连接，则返回 null
                return null;
            }
        }
        return conn;
    }

    /**
     * 查找连接池中所有的连接，查找一个可用的数据库连接， 如果没有可用的连接，返回 null
     *
     * @return 返回一个可用的数据库连接
     */
    private Connection findFreeConnection() throws SQLException {
        Connection conn = null;
        PooledConnection pConn = null;
        // 获得连接池向量中所有的对象
        Iterator iterator = connections.iterator();
        // 遍历所有的对象，看是否有可用的连接
        while (iterator.hasNext()) {
            pConn = (PooledConnection) iterator.next();
            if (!pConn.isBusy()) {

                if (pConn.getConnection().isValid(3000)) {
                    conn = pConn.getConnection();
                    pConn.setBusy(true);
                    // 测试此连接是否可用
                    pConn.setConnection(conn);

                    break; // 己经找到一个可用的连接，退出
                } else {
                    try {
                        connections.remove(pConn);
                        PooledConnection pooledConnection = new PooledConnection(newConnection());
                        connections.add(pooledConnection);
                        pooledConnection.setBusy(true);
                        conn = pooledConnection.getConnection();

                        break; // 己经找到一个可用的连接，退出
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return conn;// 返回找到到的可用连接
    }


    /**
     * 此函数返回一个数据库连接到连接池中，并把此连接置为空闲。 所有使用连接池获得的数据库连接均应在不使用此连接时返回它。
     *
     * 需返回到连接池中的连接对象
     */

    public void returnConnection(Connection conn) {
        // 确保连接池存在，如果连接没有创建（不存在），直接返回
        if (connections == null) {
            System.out.println(" 连接池不存在，无法返回此连接到连接池中 !");
            return;
        }
        PooledConnection pConn = null;
        Iterator iterator = connections.iterator();
        // 遍历所有的对象，看是否有可用的连接
        while (iterator.hasNext()) {
            pConn = (PooledConnection) iterator.next();
            // 先找到连接池中的要返回的连接对象
            if (conn == pConn.getConnection()) {
                // 找到了 , 设置此连接为空闲状态
                pConn.setBusy(false);
                break;
            }
        }
    }

    /**
     * 刷新连接池中所有的连接对象
     *
     */
    public synchronized void refreshConnections() throws SQLException {
        // 确保连接池己创新存在
        if (connections == null) {
            System.out.println(" 连接池不存在，无法刷新 !");
            return;
        }
        PooledConnection pConn = null;
        Iterator iterator = connections.iterator();
        // 遍历所有的对象，看是否有可用的连接
        while (iterator.hasNext()) {
            pConn = (PooledConnection) iterator.next();
            // 如果对象忙则等 5 秒 ,5 秒后直接刷新
            if (pConn.isBusy()) {
                wait(10); // 等 5 秒
            }
            // 关闭此连接，用一个新的连接代替它。
            closeConnection(pConn.getConnection());
            pConn.setConnection(newConnection());
            pConn.setBusy(false);
        }
    }

    /**
     * 关闭连接池中所有的连接，并清空连接池。
     */

    public synchronized void closeConnectionPool() throws SQLException {
        // 确保连接池存在，如果不存在，返回
        if (connections == null) {
            System.out.println(" 连接池不存在，无法关闭 !");
            return;
        }
        PooledConnection pConn = null;
        Iterator iterator = connections.iterator();
        while (iterator.hasNext()) {
            pConn = (PooledConnection) iterator.next();
            // 如果忙，等 5 秒
            if (pConn.isBusy()) {
                wait(10); // 等 5 秒
            }
            // 5 秒后直接关闭它
            closeConnection(pConn.getConnection());
            // 从连接池向量中删除它
            connections.remove(pConn);
        }
        // 置连接池为空
        connections = null;
    }

    /**
     * 关闭一个数据库连接
     *
     * 需要关闭的数据库连接
     */
    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(" 关闭数据库连接出错： " + e.getMessage());
        }
    }
    /**
     * 使程序等待给定的毫秒数
     *
     * 给定的毫秒数
     */

    private void wait(int mSeconds) {
        try {
            Thread.sleep(mSeconds);
        } catch (InterruptedException e) {
        }
    }
    /**
     *
     * 内部使用的用于保存连接池中连接对象的类 此类中有两个成员，一个是数据库的连接，另一个是指示此连接是否 正在使用的标志。
     */
    class PooledConnection {
        Connection connection = null;// 数据库连接
        boolean busy = false; // 此连接是否正在使用的标志，默认没有正在使用

        // 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
        public PooledConnection(Connection connection) {
            this.connection = connection;
        }

        // 返回此对象中的连接
        public Connection getConnection() {
            return connection;
        }

        // 设置此对象的，连接
        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        // 获得对象连接是否忙
        public boolean isBusy() {
            return busy;
        }

        // 设置对象的连接正在忙
        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }

}