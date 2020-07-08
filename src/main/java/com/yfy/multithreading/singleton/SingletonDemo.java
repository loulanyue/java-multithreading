package com.yfy.multithreading.singleton;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author youfyu
 * @date 2020/06/28
 * 单例模式线程不安全情况下，加入双端检锁机制，通过volatile禁止指令重排
 */
public class SingletonDemo {
    //引入guava依赖包，创建线程池
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

    private static ExecutorService taskExe = new ThreadPoolExecutor(10, 20, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);


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

        //并发多线程时，不加synchronized结果发生改变，没有唯一结果
        for (int i = 1; i <= 10; i++) {
            taskExe.execute(() -> SingletonDemo.getInstance());
        }
    }
}
