package com.wen233.demo.mywebservlet;

import com.wen233.demo.Utils.ScanClassUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: huu
 * @date: 2020/3/21 11:45
 * @description:
 */
public class AnnotationHandlerFilter implements Filter {

    private ServletContext servletContext = null;

    /**
     * ServletContext初始化时将制定目录下的类加载
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------Handler初始化开始---------");
        servletContext = filterConfig.getServletContext();
//        向Class引用添加泛型约束仅仅是为了提供编译期类型的检查从而避免将错误延续到运行时期
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>(16);
//        从xml配置中获得FilterInitParam
        String basePackage = filterConfig.getInitParameter("basePackage");
//        如果有多个目录并且用,分隔
        if (basePackage.indexOf(',') > 0) {
            String[] packageNameArr = basePackage.split(",");
            for (String packageName : packageNameArr) {
//                添加当前目录的类到Map中
                addServletClassToServletContext(packageName, classMap);
            }
        } else {
            addServletClassToServletContext(basePackage, classMap);
        }
        System.out.println("---------Handler初始化结束---------");
    }

    /**
     * 添加当前目录下的类到Map中
     * @param packageName
     * @param classMap
     */
    private void addServletClassToServletContext(String packageName, Map<String, Class<?>> classMap) {
//        获取当前目录下的类
        Set<Class<?>> classes = ScanClassUtils.getClasses(packageName);
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(MyWebServlet.class)) {
//                获取MyWebServlet的实例
                MyWebServlet annotationInstance = clazz.getAnnotation(MyWebServlet.class);
//                获取MyWebServlet的value值
                String annotationAttrValue = annotationInstance.value();
                if (!"".equals(annotationAttrValue)) {
                    classMap.put(annotationAttrValue, clazz);
                }
//                获取MySerServlet的urlPatterns
                String[] urlPatterns = annotationInstance.urlPatterns();
                for (String url : urlPatterns) {
                    if (!"".equals(url)) {
                        classMap.put(url, clazz);
                    }
                }
//                将classMap绑定到servletContext中
                servletContext.setAttribute("servletClassMap", classMap);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("---------进入注解处理器-----------");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
//        从servletContext中获取classMap
        Map<String, Class<?>> classMap = (Map<String, Class<?>>) servletContext.getAttribute("servletClassMap");
//        获得ContextPath
        String contextPath = req.getContextPath();
        System.out.println("contextPath=" + contextPath);
//        获得请求路径
        String url = req.getRequestURI();
        System.out.println("url=" + url);
        if (!url.contains("!")) {
            String reqMethod = req.getMethod();
            String reqServletName = url.substring(contextPath.length());
            System.out.println("servlet url=" + reqServletName);
//            创建Servlet实例对象
            Class<?> clazz = classMap.get(reqServletName);
            Object obj = null;
            try {
                obj = clazz.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

//            获取实例方法
            Method targetMethod = null;
            if ("get".equalsIgnoreCase(reqMethod)) {
                try {
                    targetMethod = clazz.getMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    targetMethod = clazz.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            try {
//                执行方法处理请求
                targetMethod.invoke(obj, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    @Override
    public void destroy() {

    }
}
