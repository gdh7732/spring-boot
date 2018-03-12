package com.example.javaclass;

public class Son extends Father {

    private String name;

    public Son() {
        System.out.println("子类无参构造。。。");
    }

    public Son(String name) {
        this.name = name;
        System.out.println("子类有参参构造。。。");
    }

    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }
}
