package com.yfy.multithreading.singleton;

/**
 * @author youfyu
 * @date 2020/06/28
 *  单例模式线程不安全情况下，加入双端检锁机制，通过volatile禁止指令重排
 */
public class SingletonDemo {

    private volatile static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo()");
    }

    //DCL double check lock 双端检锁机制，不一定线程安全，指令重排可能使结果不唯一
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程（main线程的操作动作）
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//
//        System.out.println("------");
//        System.out.println("------");
//        System.out.println("------");

        //并发多线程后，不加synchronized结果发生改变，没有唯一结果
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
