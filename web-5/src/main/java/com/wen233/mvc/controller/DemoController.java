package com.wen233.mvc.controller;

import com.wen233.mvc.Utils.View;
import com.wen233.mvc.Utils.ViewData;
import com.wen233.mvc.Utils.WebContext;
import com.wen233.mvc.mvcframework.Controller;
import com.wen233.mvc.mvcframework.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/21 17:15
 * @description:
 */
@Controller
public class DemoController {

    @RequestMapping("/demo")
    public View demo() {
        return new View("/hello.jsp");
    }

    @RequestMapping("/login/demo")
    public View loginDemo() {
        ViewData data = new ViewData();
        HttpServletRequest request = WebContext.requestHolder.get();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("xiao".equals(username) && "123456".equals(password)) {
            return new View("/api/login/success", "msg", username);
        } else {
            data.put("msg", "账号密码校验失败");
            return new View("/api/login/fail");
        }
    }

    @RequestMapping("/login/success")
    public void loginSuccess() throws IOException {
        HttpServletRequest request = WebContext.requestHolder.get();
        String msg = (String) request.getAttribute("msg");
        System.out.println("success msg=" + msg);
        WebContext.responseHolder.get().setCharacterEncoding("UTF-8");
        WebContext.responseHolder.get().setContentType("text/html");
        WebContext.responseHolder.get().getWriter().println("欢迎你，" + msg);
    }

    @RequestMapping("/login/fail")
    public void loginFail() throws IOException {
        HttpServletRequest request = WebContext.requestHolder.get();
        String msg = (String) request.getAttribute("msg");
        System.out.println("fail msg=" + msg);
        WebContext.responseHolder.get().setCharacterEncoding("UTF-8");
        WebContext.responseHolder.get().setContentType("text/html");
        WebContext.responseHolder.get().getWriter().println(msg);
    }
}
