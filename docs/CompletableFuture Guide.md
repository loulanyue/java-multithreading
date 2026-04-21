# CompletableFuture Guide

这是一份面向面试复习的 `CompletableFuture` 速查文档，重点不是覆盖所有 API，而是帮助你快速建立“怎么串任务、怎么处理异常、什么时候适合用”的主线理解。

## 为什么要用 CompletableFuture

`CompletableFuture` 适合处理异步任务编排。相比只拿一个 `Future` 去阻塞等待结果，它更擅长：

- 任务完成后继续串下一个步骤
- 合并多个异步结果
- 统一处理异常
- 让异步逻辑更接近“数据流”的写法

## 高频方法速查

| 方法 | 用途 |
| --- | --- |
| `supplyAsync` | 启动一个有返回值的异步任务 |
| `runAsync` | 启动一个无返回值的异步任务 |
| `thenApply` | 对上一步结果做转换 |
| `thenAccept` | 消费上一步结果但不返回新值 |
| `thenRun` | 不关心上一步结果，只在完成后继续执行 |
| `thenCompose` | 把两个依赖型异步任务串起来 |
| `thenCombine` | 合并两个独立异步任务的结果 |
| `allOf` | 等待多个任务都完成 |
| `anyOf` | 任意一个任务完成就继续 |
| `exceptionally` | 兜底处理异常 |

## `thenApply` vs `thenCompose`

- `thenApply`：上一个任务返回结果后，对结果再做一次普通转换
- `thenCompose`：上一个任务返回结果后，再基于它发起新的异步任务

可以把它理解成：

- `thenApply` 更像“值到值”
- `thenCompose` 更像“值到异步任务”

## 异常处理

常见写法：

```java
future.exceptionally(ex -> {
    System.out.println("error: " + ex.getMessage());
    return fallbackValue;
});
```

面试回答时可以强调：

- 异步任务内部抛出的异常不会像同步代码那样直接冒到主线程
- 如果不显式处理，最终通常会在 `get()` / `join()` 时暴露
- `exceptionally` 适合做兜底，`whenComplete` 适合观察结果和异常

## 面试里可以怎么说

可以按下面这个顺序回答：

1. `CompletableFuture` 用来做异步任务编排
2. 它比传统 `Future` 更适合链式处理
3. 常见能力包括串行依赖、并行汇总、异常兜底
4. 典型场景是聚合多个远程调用、异步计算、并行查询

## 使用提醒

- 不要把所有异步任务都丢到默认线程池里而不做区分
- 如果是 IO 密集型任务，最好显式指定线程池
- 面试时重点讲“为什么要用”和“如何组织异步流程”，比背方法名更重要
