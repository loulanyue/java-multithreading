package com.interview.javabasic.thread;

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread("Thread1");
        MyThread mt2 = new MyThread("Thread2");
        MyThread mt3 = new MyThread("Thread3");
        mt1.start();
        mt2.start();
        mt3.start();
    }
}
