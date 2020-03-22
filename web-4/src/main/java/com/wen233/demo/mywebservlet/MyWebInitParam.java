package com.wen233.demo.mywebservlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: huu
 * @date: 2020/3/21 11:41
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyWebInitParam {

    /**
     * 参数名
     * @return
     */
    String paramName() default "";

    /**
     * 参数值
     * @return
     */
    String paramValue() default "";
}
