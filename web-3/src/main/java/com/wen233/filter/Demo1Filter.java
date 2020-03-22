package com.wen233.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/20 09:57
 * @description:
 */
public class Demo1Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter------初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //对request和response进行一些预处理
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("FilterDemo执行前！！！==========111");
        //让目标资源执行，放行
        chain.doFilter(request, response);
        System.out.println("FilterDemo执行后！！！===========111");
    }

    @Override
    public void destroy() {
        System.out.println("Filter------销毁");
    }
}
