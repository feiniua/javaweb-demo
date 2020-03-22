package com.wen233.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: huu
 * @date: 2020/3/20 14:22
 * @description:
 */
public class FilterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        //获取请求方式
        String method = req.getMethod();
        //获取输出流
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        out.write("请求的方式："+method);
        out.write("<br/>");
        out.write("接收到的参数："+message);
    }
}
