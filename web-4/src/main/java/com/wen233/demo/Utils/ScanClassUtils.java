package com.wen233.demo.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: huu
 * @date: 2020/3/21 12:20
 * @description:
 */
public class ScanClassUtils {

    public static Set<Class<?>> getClasses(String pack) {
        Set<Class<?>> classes = new LinkedHashSet<>();
//        是否循环迭代
        boolean recursive = true;
//        获取报名并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace(".", "/");
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

            while (dirs.hasMoreElements()) {
//                得到下一元素
                URL url = dirs.nextElement();
//                得到协议
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static void findAndAddClassesInPackageByFile(String packageName, String filePath,
                                                        final boolean recursive, Set<Class<?>> classes) {
//        得到文件目录
        File dir = new File(filePath);
//        如果不存在或者不是目录则直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
//        如果存在就获取包下所有文件 包括目录
        File[] dirFiles = dir.listFiles(new FileFilter() {
//            自定义过滤规则 如果可以循环或是.class结尾的文件
            @Override
            public boolean accept(File pathname) {
                return (recursive && pathname.isDirectory())
                        || (pathname.getName().endsWith(".class"));
            }
        });
//        循环所有文件
        for (File file : dirFiles) {
//            如果是目录则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(), recursive, classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." +className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Set<Class<?>> classes = ScanClassUtils.getClasses("com.wen233.demo.servlet");
        System.out.println(classes.size());
        ScanClassUtils t = new ScanClassUtils();
        System.out.println(t.getClass());
        System.out.println(t.getClass().getClassLoader());
        System.out.println(t.getClass().getClassLoader().getResource(""));
        System.out.println(t.getClass().getClassLoader().getResource("com/wen233/demo/servlet/Servlet1.class"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResources("com/wen233").nextElement().getFile());
    }
}
