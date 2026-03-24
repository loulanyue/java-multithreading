**语言：** [English](README.md) | 简体中文

# Java 多线程笔记

💫 帮助你快速掌握 Java 多线程与并发编程的学习笔记仓库

涵盖线程生命周期、线程通信、锁机制、线程池、JUC 工具类，以及 `BlockingQueue`、`Callable/FutureTask`、`ForkJoin` 等核心主题。

适合用于面试复习、知识梳理和并发编程快速入门。

这是一个围绕 Java 并发与 `java.util.concurrent` 的学习型知识库，主要整理线程生命周期、线程通信、锁、线程池，以及 JUC 常用工具类等内容。

当前仓库已经补充了中英双语入口：

- 英文首页：[README.md](README.md)
- 文档索引：[docs/README.md](docs/README.md)

专题正文目前仍以中文为主，方便保留原始学习笔记内容；英文读者可以先通过英文首页和文档索引了解每篇文档的主题。

## 内容概览

- 线程生命周期与多线程基础认知
- 线程间通信方式
- `synchronized`、`Lock`、`volatile`、`ThreadLocal`
- 线程池核心参数与任务提交逻辑
- `CountDownLatch`、`Semaphore`、`CyclicBarrier` 等 JUC 工具
- `BlockingQueue`、`Callable/FutureTask`、`ForkJoin` 等专题笔记

## 推荐阅读路径

1. 先看本文，建立整体知识地图。
2. 再看 [docs/JUC.md](docs/JUC.md)，补线程、状态与并发工具的基础背景。
3. 按专题阅读 [docs/BlockingQueue.md](docs/BlockingQueue.md)、[docs/Callable%20FutureTask.md](docs/Callable%20FutureTask.md)、[docs/ForkJoin.md](docs/ForkJoin.md)。
4. [docs/batchRedis.md](docs/batchRedis.md) 属于实践型辅助笔记，可按需阅读。

## 文档导航

| 主题 | 说明 | 文档 |
| --- | --- | --- |
| 多线程总览 | 生命周期、线程通信、锁、线程池、并发工具 | [README.zh-CN.md](README.zh-CN.md) |
| BlockingQueue | 定义、分类、核心 API 与阻塞语义 | [docs/BlockingQueue.md](docs/BlockingQueue.md) |
| Callable / FutureTask | 与 `Runnable` 的区别、异步结果处理、使用示例 | [docs/Callable%20FutureTask.md](docs/Callable%20FutureTask.md) |
| ForkJoin | 分治思想、核心类、递归任务示例 | [docs/ForkJoin.md](docs/ForkJoin.md) |
| JUC | 进程线程区别、线程状态、并发工具综述 | [docs/JUC.md](docs/JUC.md) |
| Redis 批量测试数据 | 批量生成 Redis 测试数据的小工具说明 | [docs/batchRedis.md](docs/batchRedis.md) |

## 多线程基础

### 线程的生命周期

新建 -> 就绪 -> 运行 -> 阻塞 -> 就绪 -> 运行 -> 死亡

![线程生命周期图](https://img-blog.csdnimg.cn/20200609153121547.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

### 怎么理解多线程

1. 定义：多线程是指从软件或硬件层面支持多个线程并发执行的技术，可以提升整体处理效率。
2. 原因：单线程处理能力有限，多线程可以提高吞吐量。
3. 实现方式：在 Java 里通常通过 `Thread`、`Runnable`、`Callable` 等方式实现。
4. 代价：线程会带来创建、销毁、切换和栈空间开销，所以通常会搭配线程池复用线程。

### 线程间通信的方式

1. 等待通知机制：`wait()`、`notify()`、`join()`、`interrupted()`
2. 并发工具：`synchronized`、`Lock`、`CountDownLatch`、`CyclicBarrier`、`Semaphore`

## 锁

### 什么是锁

锁是在多个线程竞争共享资源时，用来控制访问顺序和并发安全的同步工具。只有获取锁的线程才能进入同步代码，其他线程需要等待。

### synchronized

通常和 `wait`、`notify`、`notifyAll` 一起使用。

- `wait`：释放对象锁，释放 CPU，进入等待队列，只能通过 `notify` 或 `notifyAll` 继续执行
- `sleep`：释放 CPU，但不释放对象锁，时间到后自动继续
- `notify`：唤醒等待队列中的一个线程，让其竞争锁
- `notifyAll`：唤醒等待队列中所有线程，让它们竞争锁

### Lock

`Lock` 拥有和 `synchronized` 类似的语义，但支持更多高级能力，例如可中断等待和定时等待。代价是需要手动加锁和释放锁。

### synchronized 和 Lock 的区别

- 性能：竞争激烈时 `Lock` 往往更灵活；竞争不激烈时两者差距通常不大
- 用法：`synchronized` 可直接作用于代码块和方法；`Lock` 需要显式编程控制
- 原理：`synchronized` 是 JVM 级同步；`Lock` 常基于 AQS 实现
- 功能：`ReentrantLock` 支持公平锁、条件队列、可中断等待等能力

建议：写同步逻辑时优先考虑 `synchronized`，只有在确实需要额外能力时再选择 `ReentrantLock` 或原子类。

### 常见锁分类

- 公平锁 / 非公平锁
- 可重入锁
- 独享锁 / 共享锁
- 乐观锁 / 悲观锁
- 偏向锁 / 轻量级锁 / 重量级锁
- 自旋锁 / 自适应自旋锁

## volatile

作用：

1. 让线程直接和主内存交互，保证可见性
2. 禁止 JVM 对相关指令进行重排序

## ThreadLocal

可以使用 `ThreadLocal<UserInfo>` 让每个线程维护自己的线程本地变量副本。其底层通常通过线程内部的 `ThreadLocalMap` 存储数据，从而实现线程隔离。

## 线程池

### 为什么需要线程池

- 避免频繁 `new Thread` 带来的创建和销毁开销
- 统一管理线程数量，避免无限制创建线程导致 OOM
- 提供定时执行、并发控制、拒绝策略等工程化能力

### 线程池核心参数

- `corePoolSize`：核心线程数
- `maximumPoolSize`：最大线程数
- `workQueue`：任务阻塞队列
- `keepAliveTime`：非核心线程空闲存活时间
- `unit`：时间单位
- `threadFactory`：线程工厂
- `rejectHandler`：拒绝策略

### 任务提交逻辑

以下逻辑来自 `ThreadPoolExecutor.execute`：

1. 如果运行中的线程数小于 `corePoolSize`，直接创建新线程执行任务
2. 如果运行中的线程数大于等于 `corePoolSize`：
3. 先尝试把任务放进队列
4. 如果队列满了，再判断是否还能创建非核心线程
5. 如果线程数也达到上限，则执行拒绝策略

### 阻塞队列策略

- 直接提交：例如 `SynchronousQueue`
- 无界队列：队列可无限增长，任务容易堆积
- 有界队列：例如 `ArrayBlockingQueue`，更易控制资源上限

## 并发包工具类

### CountDownLatch

计数器闭锁可以阻塞主线程，直到其他线程完成指定数量的任务后再继续执行。

![CountDownLatch 图示](https://img-blog.csdnimg.cn/20200609153230864.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

典型场景：

- 并行计算后等待汇总结果
- 统计一批并发任务是否全部完成

### Semaphore

信号量可以控制同一时间允许多少线程访问资源，适合做限流或资源并发配额控制。

```java
public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3);
                    Thread.sleep(1000);
                    System.out.println(finalI);
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
```

由于每次同时申请 3 个许可，所以即使开启 100 个线程，也会显著限制同一时刻能执行的任务数量。

典型场景：

- 数据库连接并发数控制
- 接口限流
- 固定容量资源池访问控制

### CyclicBarrier

`CyclicBarrier` 可以让一组线程互相等待，直到全部线程都到达屏障点后再一起继续执行。

![CyclicBarrier 图示](https://img-blog.csdnimg.cn/20200609153258403.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

与 `CountDownLatch` 的区别：

1. `CountDownLatch` 更像一个线程等待其他线程
2. `CyclicBarrier` 更像多个线程互相等待
3. `CyclicBarrier` 的计数器可以重复使用
