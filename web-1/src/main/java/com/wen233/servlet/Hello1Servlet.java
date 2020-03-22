package com.wen233.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author: huu
 * @date: 2020/3/18 13:55
 * @description:
 */
public class Hello1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getCharacterEncoding());
        System.out.println(resp.getCharacterEncoding());
        OutputStream outputStream = resp.getOutputStream();
        String data = "你好";
        outputStream.write(data.getBytes());
        outputStream.close();
    }
}
