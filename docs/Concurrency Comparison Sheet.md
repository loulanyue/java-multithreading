# Concurrency Comparison Sheet

这是一页面向面试复习的并发概念对比速查表，用来快速区分那些最容易混淆的知识点。

## `wait()` vs `sleep()`

| 对比项 | `wait()` | `sleep()` |
| --- | --- | --- |
| 所属类 | `Object` | `Thread` |
| 是否释放锁 | 会 | 不会 |
| 常见用途 | 线程通信、等待条件 | 暂停当前线程 |
| 是否需要同步块 | 通常需要 | 不需要 |

## `synchronized` vs `Lock`

| 对比项 | `synchronized` | `Lock` |
| --- | --- | --- |
| 使用方式 | 关键字 | 显式 API |
| 自动释放锁 | 是 | 否，需要手动 `unlock()` |
| 可中断获取锁 | 不支持 | 支持 |
| 公平锁 | 不支持 | 可选支持 |
| 适合场景 | 默认首选、简单同步 | 需要更细粒度控制 |

## `Runnable` vs `Callable`

| 对比项 | `Runnable` | `Callable` |
| --- | --- | --- |
| 是否有返回值 | 否 | 是 |
| 是否可抛受检异常 | 否 | 是 |
| 常见搭配 | `Thread`、线程池 | `FutureTask`、线程池 |

## `CountDownLatch` vs `CyclicBarrier`

| 对比项 | `CountDownLatch` | `CyclicBarrier` |
| --- | --- | --- |
| 核心模式 | 一个等多个 | 多个互相等待 |
| 是否可复用 | 否 | 是 |
| 常见场景 | 主线程等子任务完成 | 多线程分阶段协作 |

## `HashMap` vs `ConcurrentHashMap`

| 对比项 | `HashMap` | `ConcurrentHashMap` |
| --- | --- | --- |
| 线程安全 | 否 | 是 |
| 典型使用场景 | 单线程环境 | 并发读写 |
| 面试重点 | 扩容、链表/红黑树 | 分段演进、CAS、并发控制 |

## 使用建议

- 面试前先把这页完整过一遍
- 每个对比点都尝试用自己的话讲出“为什么会这样”
- 如果卡住，再回到对应专题正文补细节
