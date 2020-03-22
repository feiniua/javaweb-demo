package com.wen233.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/20 17:21
 * @description:
 */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("username") != null) {
            chain.doFilter(req, resp);
            return;
        }

        String value = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("autoLogin".equals(cookie.getName())) {
                value = cookie.getValue();
            }
        }

        if (value != null) {
            String username = value.split("\\.")[0];
            String password = value.split("\\.")[1];

            System.out.println(username);
            System.out.println(password);

            if (!("xian".equals(username) && "123456".equals(password))) {
                resp.getWriter().println("用户名错误");
                return;
            }
            req.getSession().setAttribute("username", "xian");
            req.getSession().setAttribute("password", "123456");
        }
        chain.doFilter(req, resp);
    }
}
