package com.wen233.framework.model;

/**
 * @author: huu
 * @date: 2020/3/19 19:00
 * @description:
 */
public class Account {
    private Integer id;

    private String name;

    private Float money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "name=" + name + ";money=" + money;
    }
}
