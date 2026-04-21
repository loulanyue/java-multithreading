**语言：** 简体中文（当前） | [English](README.en.md)

# Java 多线程笔记

💫 帮助你快速掌握 Java 多线程与并发编程的学习笔记仓库

涵盖线程生命周期、线程通信、锁机制、线程池、JUC 工具类，以及 `BlockingQueue`、`Callable/FutureTask`、`ForkJoin` 等核心主题。

适合用于面试复习、知识梳理和并发编程快速入门。

这是一个围绕 Java 并发与 `java.util.concurrent` 的学习型知识库。当前版本重点优化了可读性：首页负责导航，基础内容和专题内容拆分到独立文档，方便快速查阅和按主题复习。

## 从哪里开始

- 英文首页：[README.en.md](README.en.md)
- 文档索引：[docs/README.md](docs/README.md)
- 多线程基础总览：[docs/Multithreading Basics.md](docs/Multithreading%20Basics.md)

## 仓库适合谁

- 准备 Java / 后端面试，想快速搭好并发知识框架的人
- 想系统复习线程、锁、线程池和 JUC 工具的人
- 需要一个轻量、可导航、可回顾的并发学习笔记仓库的人

## 内容地图

| 模块 | 你会看到什么 | 文档 |
| --- | --- | --- |
| 多线程基础 | 生命周期、通信、锁、`volatile`、`ThreadLocal`、线程池、JUC 工具 | [docs/Multithreading Basics.md](docs/Multithreading%20Basics.md) |
| JUC 速查 | 进程 / 线程、线程状态、`wait` / `sleep`、并发 / 并行 | [docs/JUC.md](docs/JUC.md) |
| BlockingQueue | 定义、分类、核心 API、使用示例 | [docs/BlockingQueue.md](docs/BlockingQueue.md) |
| Callable / FutureTask | 异步任务返回值、阻塞获取结果、使用示例 | [docs/Callable FutureTask.md](docs/Callable%20FutureTask.md) |
| CompletableFuture | 异步编排、链式调用、异常处理速查 | [docs/CompletableFuture Guide.md](docs/CompletableFuture%20Guide.md) |
| 死锁排查 | 死锁成因、`jstack` 排查、预防思路 | [docs/Deadlock Troubleshooting.md](docs/Deadlock%20Troubleshooting.md) |
| ForkJoin | 分治思想、三大核心类型、递归任务示例 | [docs/ForkJoin.md](docs/ForkJoin.md) |
| Redis 小工具 | 批量生成 Redis 测试数据 | [docs/batchRedis.md](docs/batchRedis.md) |

## 推荐阅读顺序

1. 先看 [docs/Multithreading Basics.md](docs/Multithreading%20Basics.md)，建立整体框架。
2. 再看 [docs/JUC.md](docs/JUC.md)，补齐线程状态与基础术语。
3. 然后按专题阅读 [docs/BlockingQueue.md](docs/BlockingQueue.md)、[docs/Callable FutureTask.md](docs/Callable%20FutureTask.md)、[docs/CompletableFuture Guide.md](docs/CompletableFuture%20Guide.md)、[docs/ForkJoin.md](docs/ForkJoin.md)。
4. 补看 [docs/Deadlock Troubleshooting.md](docs/Deadlock%20Troubleshooting.md)，建立并发故障排查思路。
5. 最后按需阅读 [docs/batchRedis.md](docs/batchRedis.md) 这种实践型辅助笔记。

## 面试速查

| 主题 | 一句话记忆 |
| --- | --- |
| 生命周期 | 新建 -> 就绪 -> 运行 -> 阻塞 / 等待 -> 结束 |
| `synchronized` vs `Lock` | 默认先用 `synchronized`，有中断、公平锁、条件队列需求再考虑 `Lock` |
| `wait()` vs `sleep()` | `wait()` 会释放锁，`sleep()` 不会 |
| `volatile` | 保证可见性，并限制指令重排序 |
| `ThreadLocal` | 让每个线程维护自己的变量副本 |
| 线程池 | 用来控制线程数量、复用线程、统一拒绝策略 |
| `CountDownLatch` | 一个线程等多个线程 |
| `CyclicBarrier` | 多个线程互相等待 |
| `Semaphore` | 控制并发访问数量 |

## 当前文档结构

```text
java-multithreading/
|-- README.md
|-- README.en.md
|-- README.zh-CN.md
`-- docs/
    |-- README.md
    |-- Multithreading Basics.md
    |-- JUC.md
    |-- BlockingQueue.md
    |-- Callable FutureTask.md
    |-- ForkJoin.md
    `-- batchRedis.md
```

## 说明

- 中文是主要阅读语言，英文入口主要用于导航和快速理解
- 保留原有主题文件名，尽量减少历史链接失效
- 这是一份学习笔记型仓库，目标是“清晰、可查、可复习”，不是完整教程
