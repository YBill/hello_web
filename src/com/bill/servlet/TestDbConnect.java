package com.bill.servlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class TestDbConnect {

    public static void main(String[] args) {
        try {
            hikariCPConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void hikariCPConnect() throws SQLException {
        HikariConfig config = new HikariConfig();
        // 直接在这里使用，不写这句话也正常，但是通过Servlet访问就会报错
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/first_test");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);

        System.out.println("连接数据库...");

        Connection conn = ds.getConnection();

        select(conn);
    }

    private static void jdbcConnect() throws SQLException {
        // 注册 JDBC 驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("连接数据库...");

        // 连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/first_test",
                "root", "123456");

        select(conn);
    }

    private static void select(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println(" 实例化Statement对象...");

            stmt = conn.createStatement();

            // 查询数据
            rs = stmt.executeQuery("select * from table1");

            while (rs.next()) {
                int id = rs.getInt("id");
                String data = rs.getString("data");

                System.out.print("id：" + id);
                System.out.print("，data：" + data);
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

}
