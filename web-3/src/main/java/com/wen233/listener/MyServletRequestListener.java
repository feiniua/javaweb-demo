package com.wen233.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: huu
 * @date: 2020/3/20 16:49
 * @description:
 */
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request创建" + ((HttpServletRequest) sre.getServletRequest()).getRequestURI());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request销毁");
    }
}
