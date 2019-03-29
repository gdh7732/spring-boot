package com.thread.demo;

public class MultiRunDemo {
    public static void main(String[] args) {
        //传入了Runnable实现类
        new Thread(new MyRunnable()){
            @Override
            public void run() {
                System.out.println("我是直接覆写Thread类run方法的代码...");
            }
        }.start();
    }
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("我是实现Runnable接口的对象中的run方法的代码...");
        }
    }
}
