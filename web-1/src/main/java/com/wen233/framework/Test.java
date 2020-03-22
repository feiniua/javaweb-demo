package com.wen233.framework;

import com.wen233.framework.model.Account;

import java.sql.SQLException;

/**
 * @author: huu
 * @date: 2020/3/19 19:02
 * @description:
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        AccountDao dao = new AccountDao();
        Account account = new Account();


        System.out.println(dao.find(4));

        account.setId(4);
        account.setName("晓明");
        account.setMoney(8000f);
        dao.update(account);
    }
}
