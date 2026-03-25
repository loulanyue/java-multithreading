# ForkJoin

**Language:** English + 中文

## English Overview

This note explains the Java Fork/Join framework, which applies a divide-and-conquer approach to parallel computation.

Main points:

- what Fork/Join is and why it is useful
- the three core types: `ForkJoinPool`, `ForkJoinTask`, and `RecursiveTask`
- how a large task can be split into smaller subtasks and then merged
- a recursive summation example implemented with `RecursiveTask`

## 中文正文

## 一、什么是 ForkJoin

大数据里有 `MapReduce` 这类分而治之的思路，Java 从 JDK 1.7 开始借鉴这种思想，提供了 `ForkJoin` 框架来并行执行任务。

核心思想是：

- `Fork`：把一个大任务拆分成若干个小任务
- `Join`：把多个小任务的结果合并起来，得到最终结果

## 二、三大核心类型

### 2.1 ForkJoinPool

分支合并池，类比普通线程池。

![ForkJoinPool 图示](https://img-blog.csdnimg.cn/2020042221294435.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

### 2.2 ForkJoinTask

`ForkJoinTask` 可以类比为 `FutureTask`。

![ForkJoinTask 图示](https://img-blog.csdnimg.cn/20200422213025136.png)

### 2.3 RecursiveTask

递归任务，继承后可以通过递归方式不断拆分任务。

![RecursiveTask 图示](https://img-blog.csdnimg.cn/20200422213043897.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

## 三、使用示例

```java
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int middle = (begin + end) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }

        return result;
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        System.out.println(forkJoinTask.get());

        forkJoinPool.shutdown();
    }
}
```

## 小结

`ForkJoin` 适合可以被不断拆分、并且最终能够汇总结果的任务。典型场景是大规模计算、递归计算、分治求和等。
