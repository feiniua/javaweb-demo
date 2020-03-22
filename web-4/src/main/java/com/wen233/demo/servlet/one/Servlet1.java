package com.wen233.demo.servlet.one;

import com.wen233.demo.mywebservlet.MyWebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/21 15:56
 * @description:
 */
@MyWebServlet("/demo2")
public class Servlet1 {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("one路径下Servlet");
    }
}
