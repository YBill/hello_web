package com.bill.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {

    private String message;

    @Override
    public void init() throws ServletException {
        super.init();
        message = "Hello, Servlet!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>" + message + "</h1>");

        JSONObject obj = new JSONObject();
        obj.put("id", "123");
        obj.put("name", "Bill");
        // 返回json数据，会将上面 write 输出内容覆盖
        writer.write(obj.toJSONString());

    }
}
