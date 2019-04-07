package com.interview.javabasic.thread;

public class SyncBlockAndMethod {
    public void syncsTask() {
        //同步代码库
        synchronized (this) {
            System.out.println("Hello");
            synchronized (this){
                System.out.println("World");
            }
        }
    }

    public synchronized void syncTask() {
        System.out.println("Hello Again");
    }

}
