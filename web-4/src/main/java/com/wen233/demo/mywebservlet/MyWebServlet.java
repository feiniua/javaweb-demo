package com.wen233.demo.mywebservlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: huu
 * @date: 2020/3/21 11:35
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyWebServlet {

    /**
     * Servlet的访问url
     * @return
     */
    String value();

    /**
     * Servlet的访问url
     * @return
     */
    String[] urlPatterns() default {""};

    /**
     * 描述
     * @return
     */
    String description() default "";

    /**
     * Servlet的显示名称
     * @return
     */
    String displayName() default "";

    /**
     * Servlet的名称
     * @return
     */
    String name() default "";

    /**
     * servlet的初始化参数
     * @return
     */
    MyWebInitParam[] initParams() default {};
}
