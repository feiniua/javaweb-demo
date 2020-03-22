package com.wen233.servlet2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: huu
 * @date: 2020/3/19 12:55
 * @description:
 */
public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            File file = new File("E:/IdeaProjects/demo-web/web-1/src/main/resources/db.properties");
            InputStream in = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if(resultSet!=null){
            try{
                //关闭存储查询结果的ResultSet对象
                resultSet.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if(statement!=null){
            try{
                //关闭负责执行SQL命令的Statement对象
                statement.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try{
                //关闭Connection数据库连接对象
                connection.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
