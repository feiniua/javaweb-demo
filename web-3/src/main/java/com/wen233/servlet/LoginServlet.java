package com.wen233.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/20 17:12
 * @description:
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (req.getSession().getAttribute("username") != null) {
            username = (String) req.getSession().getAttribute("username");
            password = (String) req.getSession().getAttribute("password");
        }

        if (!("xian".equals(username) && "123456".equals(password))) {
            resp.getWriter().println("用户名错误");
            return;
        }
        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("password", password);
        Cookie cookie = new Cookie("autoLogin", username + "." + password);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
        resp.getWriter().println("登录成功！");
    }
}
