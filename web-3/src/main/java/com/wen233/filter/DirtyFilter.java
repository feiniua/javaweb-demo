package com.wen233.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huu
 * @date: 2020/3/20 15:34
 * @description:
 */
public class DirtyFilter implements Filter {

    private FilterConfig filterConfig = null;
    //设置默认的字符编码
    private String defaultCharset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //得到过滤器的初始化配置信息
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //得到在web.xml中配置的字符编码
        String charset = defaultCharset;
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);

        AdvancedRequest requestWrapper = new AdvancedRequest(request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }

    class AdvancedRequest extends HttpServletRequestWrapper {

        private List<String> dirtyWords = getDirtyWords();

        //定义一个变量记住被增强对象(request对象是需要被增强的对象)
        private HttpServletRequest request;
        //定义一个构造函数，接收被增强对象
        public AdvancedRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        /* 覆盖需要增强的getParameter方法
         * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
         */
        @Override
        public String getParameter(String name) {
            try{
                //获取参数的值
                String value= this.request.getParameter(name);
                if(value==null){
                    return null;
                }

                for(String dirtyWord : dirtyWords){
                    if(value.contains(dirtyWord)){
                        System.out.println("内容中包含敏感词："+dirtyWord+"，将会被替换成****");
                        //替换敏感字符
                        value = value.replace(dirtyWord, "****");
                    }
                }
                return value;
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @Method: getDirtyWords
     * @Description: 获取敏感字符
     * @Anthor:孤傲苍狼
     *
     * @return
     */
    private List<String> getDirtyWords(){
        List<String> dirtyWords = new ArrayList<String>();
        String dirtyWordPath = filterConfig.getInitParameter("dirtyWord");
        InputStream inputStream = filterConfig.getServletContext().getResourceAsStream(dirtyWordPath);
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(inputStream,defaultCharset);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(is);
        String line;
        try {
            while ((line = reader.readLine())!= null) {//如果 line为空说明读完了
                dirtyWords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirtyWords;
    }
}
