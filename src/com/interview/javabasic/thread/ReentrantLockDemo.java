package com.interview.javabasic.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements  Runnable{
    private static ReentrantLock lock = new ReentrantLock(false);
    @Override
    public void run(){
        while (true){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rtld = new ReentrantLockDemo();
        Thread thread1 = new Thread(rtld);
        Thread thread2 = new Thread(rtld);
        thread1.start();
        thread2.start();
    }
}
