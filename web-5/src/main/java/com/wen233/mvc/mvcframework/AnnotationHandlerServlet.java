package com.wen233.mvc.mvcframework;


import com.wen233.mvc.Utils.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author: huu
 * @date: 2020/3/21 15:42
 * @description:
 */
public class AnnotationHandlerServlet extends HttpServlet {

    private String pareRequestUrl(HttpServletRequest request) {
        String path = request.getContextPath() + "/";
        String requestUrl = request.getRequestURI();
        String midUrl = requestUrl.replaceFirst(path, "");
        System.out.println("midUrl=" + midUrl);
        String lastUrl = midUrl.substring(midUrl.indexOf("/"));
        System.out.println("lastUrl=" + lastUrl);
        return lastUrl;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.execute(req, resp);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) {
        WebContext.requestHolder.set(request);
        WebContext.responseHolder.set(response);

//        解析url
        String lastUrl = pareRequestUrl(request);
//        获取要使用的类
        Class<?> clazz = RequestMappingMap.getRequestMap().get(lastUrl);
//        创建类的实例
        Object classInstance = BeanUtils.instanceClass(clazz);
//        获取类中定义的方法
        Method[] methods = BeanUtils.findDeclareMethods(clazz);
        Method method = null;
        for (Method m : methods) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                String anoPath = m.getAnnotation(RequestMapping.class).value();
                if (anoPath != null && lastUrl.equals(anoPath.trim())) {
                    method = m;
                    break;
                }
            }
        }

        try {
            if (method != null) {
//                执行目标方法处理请求
                Object retObject = method.invoke(classInstance);
                if (retObject != null) {
                    View view = (View) retObject;
//                    判断要使用的跳转方式
                    if ("forward".equals(view.getDisPathAction())) {
                        request.getRequestDispatcher(view.getUrl()).forward(request, response);
                    } else if ("redirect".equals(view.getDisPathAction())) {
                        response.sendRedirect(request.getContextPath() + view.getUrl());
                    } else {
                        request.getRequestDispatcher(view.getUrl()).forward(request, response);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        System.out.println("--------Handler初始化开始------");
        String basePackage = config.getInitParameter("basePackage");
        if (basePackage.indexOf(',') > 0) {
            String[] packageNameArr = basePackage.split(",");
            for (String packageName : packageNameArr) {
//                添加当前目录的类到Map中
                initRequestMappingMap(packageName);
            }
        } else {
            initRequestMappingMap(basePackage);
        }
        System.out.println("---------Handler初始化结束---------");
    }

    private void initRequestMappingMap(String packageName) {
        Set<Class<?>> classes = ScanClassUtils.getClasses(packageName);
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                Method[] methods = BeanUtils.findDeclareMethods(clazz);
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        String anoPath = method.getAnnotation(RequestMapping.class).value();
                        if (anoPath != null && !"".equals(anoPath.trim())) {
                            if (RequestMappingMap.getRequestMap().containsKey(anoPath)) {
                                throw new RuntimeException("RequestMapping映射的地址不能重复");
                            }
                            RequestMappingMap.getRequestMap().put(anoPath, clazz);
                        }
                    }
                }
            }
        }
    }
}
