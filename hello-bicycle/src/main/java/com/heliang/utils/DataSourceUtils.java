package com.heliang.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtils {

    private static final DataSource dataSource = new ComboPooledDataSource();

    private static final ThreadLocal<Connection> tc = new ThreadLocal<>();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection con = tc.get();
        if (con == null) {
            con = dataSource.getConnection();
            tc.set(con);
        }
        return con;
    }

    public static void release() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.commit();
            tc.remove();
            con.close();
        }
    }
}

