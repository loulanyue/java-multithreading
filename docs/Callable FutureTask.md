# Callable and FutureTask

**Language:** English + 中文

## English Overview

This note compares several ways to create or run concurrent tasks in Java, then focuses on `Callable` and `FutureTask`.

Main points:

- common ways to start concurrent work: `Thread`, `Runnable`, `Callable`, and thread pools
- differences between `Callable` and `Runnable`
- what `FutureTask` is and when to use it
- why `get()` can block and why it is usually called near the end of the main flow
- a simple example that starts asynchronous tasks and retrieves results

## 中文正文

## 一、获得多线程的方式

常见方式包括：

1. 继承 `Thread` 类
2. 实现 `Runnable` 接口
3. Java 5 之后实现 `Callable` 接口
4. 通过线程池提交任务

## 二、Callable 接口与 Runnable 接口的区别

- `Callable` 有返回值，`Runnable` 没有
- `Callable` 可以抛出受检异常，`Runnable` 不行
- 一个实现的是 `call()` 方法，一个实现的是 `run()` 方法

## 三、FutureTask 是什么

简言之，`FutureTask` 表示“未来某个时间点才能拿到结果的任务”，适合异步执行耗时操作。

可以把它理解成：

- 主线程先继续做自己的事情
- 后台线程异步计算结果
- 主线程在需要结果时再通过 `get()` 取回

它解决的是“想异步执行任务，但最终又要拿到结果”的问题。

## 四、FutureTask 原理

当主线程中有比较耗时的操作，但又不想阻塞主线程时，可以把这些任务交给 `FutureTask` 在后台完成。

特点：

- 主线程可以先执行其他逻辑
- 只有在真正需要结果时才调用 `get()`
- 如果任务还没完成，`get()` 会阻塞
- 任务完成后，结果只会计算一次

一个常见经验是：

- `get()` 尽量放在流程后面
- 不要一创建任务就立刻 `get()`，否则异步优势会被抵消

## 五、实现示例

```java
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 200;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            TimeUnit.SECONDS.sleep(4);
            return 1024;
        });
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            TimeUnit.SECONDS.sleep(4);
            return 2048;
        });

        new Thread(futureTask, "zhang3").start();
        new Thread(futureTask2, "li4").start();

        while (!futureTask.isDone()) {
            System.out.println("***wait");
        }
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + " come over");
    }
}
```

## 小结

如果你的任务需要返回值，或者需要在主线程稍后获取异步执行结果，那么 `Callable` + `FutureTask` 是比 `Runnable` 更合适的选择。
