package com.wen233.mvc.Utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: huu
 * @date: 2020/3/21 15:59
 * @description:
 *      用于存储当前线程中的request response
 */
public class WebContext {

    public static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();

    public HttpServletRequest getRequest() {
        return requestHolder.get();
    }

    public HttpServletResponse getResponse() {
        return responseHolder.get();
    }

    public HttpSession getSession() {
        return requestHolder.get().getSession();
    }

    public ServletContext getServletContext() {
        return requestHolder.get().getSession().getServletContext();
    }
}
