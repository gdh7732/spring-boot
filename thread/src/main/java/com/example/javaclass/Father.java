package com.example.javaclass;

public class Father {

    private String name;

    public Father() {
        System.out.println("父类无参构造。。。");
    }

    public Father(String name) {
        this.name = name;
        System.out.println("父类有参参构造。。。");
    }

    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }
}
