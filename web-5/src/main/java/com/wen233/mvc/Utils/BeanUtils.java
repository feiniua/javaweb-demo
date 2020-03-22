package com.wen233.mvc.Utils;

import java.lang.reflect.*;

/**
 * @author: huu
 * @date: 2020/3/21 16:34
 * @description:
 */
public class BeanUtils {

    /**
     * 实例化一个Class
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T instanceClass(Class<T> clazz) {
        if (!clazz.isInterface()) {
            try {
                return clazz.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    public static <T> T instanceClass(Constructor<T>constructor, Object... args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        setAccessible(constructor);
        return constructor.newInstance(args);
    }

    /**
     * 设置可访问性
     * @param constructor
     */
    public static void setAccessible(Constructor<?> constructor) {
        if (!Modifier.isPublic(constructor.getModifiers())
                || !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())
                && !constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
    }

    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
//            返回共有的方法
            return findDeclaredMethod(clazz, methodName, paramTypes);
        }
    }

    public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        try {
            return clazz.getDeclaredMethod(methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
            }
            return null;
        }
    }

    public static Method[] findDeclareMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    public static Field[] findDeclareFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }
}
