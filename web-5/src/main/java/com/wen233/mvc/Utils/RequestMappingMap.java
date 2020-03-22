package com.wen233.mvc.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huu
 * @date: 2020/3/21 16:08
 * @description:
 *
 */
public class RequestMappingMap {

    /**
     * RequestMapping的value值 跟 Controller的类
     */
    private static Map<String, Class<?>> requestMap = new HashMap<>();

    public static Class<?> getClassName(String path) {
        return requestMap.get(path);
    }

    public static void put(String path, Class<?> className) {
        requestMap.put(path, className);
    }

    public static Map<String, Class<?>> getRequestMap() {
        return requestMap;
    }
}
