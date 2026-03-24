# JUC

**Language:** English + 中文

## English Overview

`JUC` is short for `java.util.concurrent`, the Java standard package that provides concurrency utilities such as locks, thread pools, synchronizers, and concurrent collections.

This note focuses on five foundational ideas:

1. the difference between a process and a thread
2. simple daily-life examples for understanding multiple processes and multiple threads
3. Java thread states defined by `Thread.State`
4. the practical difference between `wait()` and `sleep()`
5. the difference between concurrency and parallelism

## Key Points in English

### Process vs Thread

- A process is an independently running program with its own resource space.
- A thread is a smaller execution unit inside a process.
- A process can contain multiple threads that share the process resources.

### Everyday intuition

- Running QQ, Word, and a music player at the same time is an example of multiple processes.
- Spell checking and autosave inside the same Word process are closer to multiple threads in one process.

### Java thread states

- `NEW`: thread created but not started
- `RUNNABLE`: ready to run or running on the CPU
- `BLOCKED`: waiting to acquire a monitor lock
- `WAITING`: waiting indefinitely for another thread action
- `TIMED_WAITING`: waiting for a limited amount of time
- `TERMINATED`: finished execution

### `wait()` vs `sleep()`

- `wait()` releases the monitor lock and enters a waiting state.
- `sleep()` pauses execution but does not release the lock currently held by the thread.

### Concurrency vs Parallelism

- Concurrency means multiple tasks are making progress over the same period and may compete for the same resource.
- Parallelism means multiple tasks execute at the same time, usually on different cores or workers, and results are later combined.

## 中文正文

JUC 全称：`java.util.concurrent`，指在并发编程中使用的工具类。

## 一、进程和线程区别

**进程：** 进程是一个具有一定独立功能的程序关于某个数据集合的一次运行活动。它是操作系统动态执行的基本单元，在传统操作系统中，进程既是基本的分配单元，也是基本的执行单元。

**线程：** 通常在一个进程中可以包含若干个线程，当然一个进程中至少有一个线程。线程可以利用进程所拥有的资源。在引入线程的操作系统中，通常把进程作为分配资源的基本单位，而把线程作为独立运行和独立调度的基本单位。由于线程比进程更小，基本上不拥有系统资源，所以调度开销更小，也更适合提升并发执行效率。

## 二、进程和线程的例子

- 使用 QQ 时，系统里会有一个 `QQ.exe` 进程。你可以同时聊天、视频、传文件、发语音，这些可以帮助理解一个应用内部的多任务执行。
- 写论文时，同时打开 Word、QQ 音乐和 QQ 聊天，这更像多个进程并行存在。
- Word 的自动恢复和拼写检查，则更接近一个进程内部多个线程协作完成不同工作。

## 三、线程状态

Java 里常见的线程状态来自 `Thread.State`：

```java
public enum State {
    NEW,
    RUNNABLE,
    BLOCKED,
    WAITING,
    TIMED_WAITING,
    TERMINATED
}
```

可以结合中文记忆：

- `NEW`：新建
- `RUNNABLE`：就绪 / 运行中
- `BLOCKED`：阻塞
- `WAITING`：一直等待
- `TIMED_WAITING`：限时等待
- `TERMINATED`：终止

## 四、wait / sleep 的区别

可以这样记：

**`wait` 放开手去睡，放开手里的锁；`sleep` 握紧手去睡，醒了手里还有锁。**

更准确地说：

- `wait()` 会释放当前持有的对象监视器锁，并进入等待队列
- `sleep()` 只让出 CPU，不释放当前已经持有的锁

## 五、什么是并发，什么是并行

**并发：** 同一时间段内多个线程访问同一个资源，强调“交替推进”和资源竞争。

例子：

- 限量抢购
- 春运抢票
- 电商秒杀

**并行：** 多项工作同时执行，强调“真正同时做事”，最后再汇总结果。

例子：

- 烧水的同时拆调料包、准备泡面

## 小结

如果把 JUC 当作并发编程入门的工具箱，那么先理解“进程 / 线程 / 状态 / wait 和 sleep / 并发与并行”这些基础概念，会更容易读懂后面的锁、线程池和同步器。
