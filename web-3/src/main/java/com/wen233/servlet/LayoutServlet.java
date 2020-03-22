package com.wen233.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/20 18:35
 * @description:
 */
public class LayoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("password");
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.getWriter().println("注销成功");
    }
}
