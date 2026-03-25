**语言：** 简体中文 | [English](README.en.md)

# Java 多线程笔记

💫 帮助你快速掌握 Java 多线程与并发编程的学习笔记仓库

涵盖线程生命周期、线程通信、锁机制、线程池、JUC 工具类，以及 `BlockingQueue`、`Callable/FutureTask`、`ForkJoin` 等核心主题。

适合用于面试复习、知识梳理和并发编程快速入门。

这个文件是中文镜像入口。如果你习惯从 `README.zh-CN.md` 进入仓库，可以继续使用这里；内容会和根 [README.md](README.md) 保持同一套中文导航思路。

## 从哪里开始

- 中文首页：[README.md](README.md)
- 英文首页：[README.en.md](README.en.md)
- 文档索引：[docs/README.md](docs/README.md)
- 多线程基础总览：[docs/Multithreading Basics.md](docs/Multithreading%20Basics.md)

## 内容地图

| 模块 | 你会看到什么 | 文档 |
| --- | --- | --- |
| 多线程基础 | 生命周期、通信、锁、`volatile`、`ThreadLocal`、线程池、JUC 工具 | [docs/Multithreading Basics.md](docs/Multithreading%20Basics.md) |
| JUC 速查 | 进程 / 线程、线程状态、`wait` / `sleep`、并发 / 并行 | [docs/JUC.md](docs/JUC.md) |
| BlockingQueue | 定义、分类、核心 API、使用示例 | [docs/BlockingQueue.md](docs/BlockingQueue.md) |
| Callable / FutureTask | 异步任务返回值、阻塞获取结果、使用示例 | [docs/Callable FutureTask.md](docs/Callable%20FutureTask.md) |
| ForkJoin | 分治思想、三大核心类型、递归任务示例 | [docs/ForkJoin.md](docs/ForkJoin.md) |
| Redis 小工具 | 批量生成 Redis 测试数据 | [docs/batchRedis.md](docs/batchRedis.md) |

## 推荐阅读顺序

1. 先看 [docs/Multithreading Basics.md](docs/Multithreading%20Basics.md)，建立整体框架。
2. 再看 [docs/JUC.md](docs/JUC.md)，补齐线程状态与基础术语。
3. 然后按专题阅读 [docs/BlockingQueue.md](docs/BlockingQueue.md)、[docs/Callable FutureTask.md](docs/Callable%20FutureTask.md)、[docs/ForkJoin.md](docs/ForkJoin.md)。
4. 最后按需阅读 [docs/batchRedis.md](docs/batchRedis.md)。

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
