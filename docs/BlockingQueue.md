# BlockingQueue

**Language:** English + 中文

## English Overview

A `BlockingQueue` is a queue designed for concurrent producer-consumer scenarios.

Its key feature is that queue operations may block:

- when the queue is empty, consumers may wait for data
- when the queue is full, producers may wait for free space

This makes it a very practical building block for thread pools, task dispatch, buffering, and producer-consumer pipelines.

## Key Points in English

### Why it matters

Before the concurrency utilities in modern Java were widely used, developers had to manually coordinate waiting, wake-up, and thread-safe queue access. `BlockingQueue` packages those concerns into a standard abstraction.

### Common implementations

- `ArrayBlockingQueue`: bounded queue backed by an array
- `LinkedBlockingQueue`: linked-list-based queue, often effectively unbounded by default
- `PriorityBlockingQueue`: priority-ordered unbounded queue
- `DelayQueue`: delay-based unbounded queue
- `SynchronousQueue`: queue with no internal capacity
- `LinkedTransferQueue`: linked-list-based unbounded transfer queue
- `LinkedBlockingDeque`: double-ended blocking queue

### Four API styles

| Style | Insert | Remove | Behavior |
| --- | --- | --- | --- |
| Exception | `add` | `remove` | Throws when the operation cannot be completed |
| Special value | `offer` | `poll` | Returns `false` or `null` |
| Blocking | `put` | `take` | Waits until it can proceed |
| Timeout | `offer(timeout)` | `poll(timeout)` | Waits up to a limit, then gives up |

## 中文正文

## 一、什么是阻塞队列

阻塞队列本质上是一个队列，在多线程场景下可以自动处理“没数据时怎么等”和“满了之后怎么等”的问题。

从数据结构角度可以这样理解：

- 栈：先进后出，后进先出
- 队列：先进先出
- 阻塞：必须等待条件满足后才能继续

![阻塞队列示意图](https://img-blog.csdnimg.cn/20200420194232932.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

当队列为空时，从队列中获取元素的操作会被阻塞。  
当队列满时，向队列中添加元素的操作会被阻塞。

也就是说：

- 试图从空队列中取数据的线程，会一直等到其他线程放入新元素
- 试图向满队列中放数据的线程，会一直等到其他线程移除元素腾出空间

## 二、阻塞队列的作用

在多线程领域里，所谓阻塞，就是某些条件不满足时线程会被挂起，等条件满足后再自动恢复。

### 2.1 为什么需要 BlockingQueue

使用 `BlockingQueue` 的好处是：

- 不需要手动控制什么时候阻塞线程
- 不需要手动决定什么时候唤醒线程
- 队列本身已经帮你处理了线程安全和等待机制

在 `concurrent` 包出现之前，这些细节都要开发者自己写，既麻烦，又容易出错。

### 2.2 阻塞队列的分类

- `ArrayBlockingQueue`：数组结构组成的有界阻塞队列
- `LinkedBlockingQueue`：链表结构组成的阻塞队列，默认容量很大
- `PriorityBlockingQueue`：支持优先级排序的无界阻塞队列
- `DelayQueue`：使用优先级队列实现的延迟无界阻塞队列
- `SynchronousQueue`：不存储元素的阻塞队列
- `LinkedTransferQueue`：链表结构组成的无界阻塞队列
- `LinkedBlockingDeque`：链表结构组成的双向阻塞队列

### 2.3 BlockingQueue 核心方法

![BlockingQueue 核心方法](https://img-blog.csdnimg.cn/20200420194613143.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)

| 类型 | 插入 / 移除方式 | 行为 |
| --- | --- | --- |
| 抛出异常 | `add` / `remove` | 队列满或空时直接抛异常 |
| 特殊值 | `offer` / `poll` | 失败时返回 `false` 或 `null` |
| 一直阻塞 | `put` / `take` | 一直等待直到操作可以完成 |
| 超时退出 | `offer(timeout)` / `poll(timeout)` | 等待一定时间后返回 |

补充说明：

- 当阻塞队列满时，再调用 `add` 插入元素，会抛 `IllegalStateException`
- 当阻塞队列空时，再调用 `remove` 移除元素，会抛 `NoSuchElementException`
- `put` 和 `take` 适合强同步场景
- 带超时的 `offer` / `poll` 更适合不想无限等待的业务

## 三、使用示例

```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x", 3L, TimeUnit.SECONDS));
    }
}
```

这个例子里：

- 队列容量是 `3`
- 前三个元素可以直接插入
- 第四次插入会等待最多 `3` 秒
- 如果超时还没有空间，就返回失败

## 小结

`BlockingQueue` 是生产者-消费者模型里最常见的并发工具之一。它把“排队、等待、唤醒、线程安全”这些麻烦事统一封装起来，是理解线程池和任务调度的一个重要基础。
