package com.bill.servlet;

import java.sql.*;

public class TestDbConnect {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("连接数据库...");

            // 连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/first_test",
                    "root", "123456");

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

        } catch (ClassNotFoundException | SQLException e) {
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
