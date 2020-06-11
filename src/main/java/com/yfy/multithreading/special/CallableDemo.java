package com.yfy.multithreading.special;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Mythread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("*****come in call method()");
        return 1024;
    }
}


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new Mythread());

        new Thread(futureTask, "A").start();

        Integer result = futureTask.get();
        System.out.println(result);
    }
}
