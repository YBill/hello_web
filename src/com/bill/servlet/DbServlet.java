package com.bill.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/DbServlet")
public class DbServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = ConnectionPool.getInstance().getDataSource().getConnection();
             Statement stmt = conn.createStatement()) {

            final String sql = "select * from table1";

            try (ResultSet rs = stmt.executeQuery(sql)) {

                JSONArray array = new JSONArray();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String data = rs.getString("data");

                    JSONObject obj = new JSONObject();
                    obj.put("id", id);
                    obj.put("data", data);
                    array.add(obj);
                }

                PrintWriter writer = resp.getWriter();
                writer.write(array.toString());

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
