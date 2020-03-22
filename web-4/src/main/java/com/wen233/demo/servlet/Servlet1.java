package com.wen233.demo.servlet;

import com.wen233.demo.mywebservlet.MyWebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/21 12:42
 * @description:
 */
@MyWebServlet("/demo")
public class Servlet1 {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Servlet @MyWebServlet");
    }
}
