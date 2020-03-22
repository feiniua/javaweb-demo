package com.wen233.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huu
 * @date: 2020/3/20 14:53
 * @description:
 */
public class HtmlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        MyHtmlRequest myrequest = new MyHtmlRequest(request);
        chain.doFilter(myrequest, response);

    }

}

/**
 * @ClassName: MyHtmlRequest
 * @Description: 使用Decorator模式包装request对象，实现html标签转义功能
 * @author: 孤傲苍狼
 * @date: 2014-9-2 下午11:29:09
 *
 */
class MyHtmlRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public MyHtmlRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    /* 覆盖需要增强的getParameter方法
     * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
     */
    @Override
    public String getParameter(String name) {
        String value = this.request.getParameter(name);
        if (value == null) {
            return null;
        }
        //调用filter转义value中的html标签
        return filter(value);
    }

    /**
     * @Method: filter
     * @Description: 过滤内容中的html标签
     * @Anthor:孤傲苍狼
     * @param message
     * @return
     */
    public String filter(String message) {
        if (message == null){
            return null;
        }
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return result.toString();
    }
}
