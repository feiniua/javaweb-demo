package com.wen233.framework;

import com.wen233.framework.model.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * Aurther
 */
public class AccountDao {

    public void add(Account account) throws SQLException{
        String sql = "insert into account(name,money) values(?,?)";
        Object[] params = {account.getName(),account.getMoney()};
        JdbcFrame.update(sql, params);
    }


    public void delete(int id) throws SQLException{
        String sql = "delete from account where id=?";
        Object[] params = {id};
        JdbcFrame.update(sql, params);
    }

    public void update(Account account) throws SQLException{

        String sql = "update account set name=?,money=? where id=?";
        Object[] params = {account.getName(),account.getMoney(),account.getId()};
        JdbcFrame.update(sql, params);

    }

    public Account find(int id) throws SQLException{
        String sql = "select * from account where id=?";
        Object[] params = {id};
        return (Account) JdbcFrame.query(sql, params, new BeanHandler(Account.class));
    }

    public List<Account> getAll() throws SQLException{
        String sql = "select * from account";
        Object[] params = {};
        return (List<Account>) JdbcFrame.query(sql, params,new BeanListHandler(Account.class));
    }
}