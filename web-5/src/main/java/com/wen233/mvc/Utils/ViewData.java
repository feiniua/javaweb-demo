package com.wen233.mvc.Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: huu
 * @date: 2020/3/21 17:03
 * @description:
 */
public class ViewData {

    private HttpServletRequest request;

    public ViewData() {
        initRequest();
    }

    private void initRequest() {
        this.request = WebContext.requestHolder.get();
    }

    public void put(String name, Object value) {
        this.request.setAttribute(name, value);
    }
}
