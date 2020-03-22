package com.wen233.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/18 14:01
 * @description:
 */
public class ServletLifecycle implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet正在初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //专门向客服端提供响应的方法
        System.out.println("Servlet正在提供服务");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet正在销毁");
    }
}
