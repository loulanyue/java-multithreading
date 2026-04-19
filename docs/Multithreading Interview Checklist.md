# Multithreading Interview Checklist

这份清单适合在面试前快速自查，确认自己是否已经掌握 Java 多线程中最常被追问的主线问题。

## 基础主线

- 能否清楚解释线程生命周期
- 能否区分并发与并行
- 能否说明 `start()` 和 `run()` 的区别
- 能否解释 `wait()`、`notify()`、`sleep()` 的差异

## 线程安全

- 能否解释什么是竞态条件
- 能否说清 `synchronized` 的作用和局限
- 能否说明 `volatile` 解决了什么、没解决什么
- 能否举例说明原子性、可见性、有序性

## 锁与并发工具

- 能否比较 `synchronized` 和 `Lock`
- 能否讲清 `ReentrantLock`、`ReadWriteLock` 的适用场景
- 能否解释 `CountDownLatch`、`CyclicBarrier`、`Semaphore`
- 能否说明 CAS 和乐观锁的基本思路

## 线程池

- 能否说清线程池为什么重要
- 能否解释核心参数：核心线程数、最大线程数、阻塞队列、拒绝策略
- 能否说明为什么不建议直接使用 `Executors` 默认工厂

## 高频专题

- `BlockingQueue` 的常见实现和使用场景
- `Callable` / `FutureTask` 和 `Runnable` 的区别
- `CompletableFuture` 的基本链式调用思路
- `ForkJoin` 的适用场景

## 口述建议

- 每个问题尽量按“定义 -> 作用 -> 典型场景 -> 注意点”回答
- 如果能结合项目或线上问题说明，会比纯概念更有说服力
- 回答锁和线程池时，优先强调取舍而不是只背 API
