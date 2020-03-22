package com.wen233.servlet2;

import java.sql.*;

/**
 * @author: huu
 * @date: 2020/3/19 12:20
 * @description:
 */
public class JDBCConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123456";
//        加载
//        Class.forName("com.mysql.jdbc.Driver");驱动
//        使用了ServiceLoader注册了驱动不需要手动注册了
//        获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
//        获取用于向数据库发送sql语句的statement
        Statement statement = connection.createStatement();
//        sql语句
        String sql = "select id,name,password,email,birthday from users";
//        执行结果
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("password=" + resultSet.getObject("password"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birthday=" + resultSet.getObject("birthday"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
