package com.example.thread;


/**
 * 演示多个线程可以并发执行的案例
 */
public class ThreadDemo {
    public static void main(String[] args) {
        //创建一个线程对象，覆盖其run方法，传入参数为线程的名字
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    System.out.println("自定义线程循环：" + i + "次");
                }
            }
        };
        //调用start方法启动线程
        t1.start();
        for (int i = 1; i <= 1000; i++) {
            System.out.println("主线程循环：" + i + "次");
        }
    }
}
