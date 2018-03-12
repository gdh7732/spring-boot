package com.example.thread;

public class CurrentThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        new Thread("custom thread"){
            @Override
            public void run() {
                System.out.println("当前线程:"+Thread.currentThread().getName());
            }
        }.start();
        Thread.sleep(100);
        System.out.println("当前线程:"+Thread.currentThread().getName());
    }

}
