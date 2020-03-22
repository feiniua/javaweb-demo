package com.wen233.servlet;


/**
 * @author: huu
 * @date: 2020/3/18 17:46
 * @description:
 */
public class Demo1 implements Demo {

    private Demo demo;

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void setAge(int age) {
        this.demo.setAge(age);
    }

    @Override
    public int getAge() {
        return this.demo.getAge();
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.setDemo(new Demo1() {
            int age = 1;

            @Override
            public void setAge(int age) {
                this.age = age;
            }

            @Override
            public int getAge() {
                return this.age;
            }
        });
        demo1.setAge(3);
        System.out.println(demo1.getAge());
    }
}
