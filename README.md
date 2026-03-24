**Language:** English | [简体中文](README.zh-CN.md)

# Java Multithreading Notes

💫 A practical note repository to help you quickly learn Java multithreading and concurrent programming

Covers thread lifecycle, thread communication, locking, thread pools, JUC utilities, and core topics such as `BlockingQueue`, `Callable/FutureTask`, and `ForkJoin`.

Well suited for interview prep, knowledge review, and a fast start in Java concurrency.

A compact knowledge base for Java concurrency and `java.util.concurrent` topics.

This repository is primarily a set of study notes about thread lifecycle, locks, thread pools, JUC utilities, and common concurrency patterns. The detailed topic documents are still written in Chinese, but this README and the docs index now provide an English-friendly entry point.

## What is included

- Thread lifecycle and core multithreading concepts
- Thread communication and synchronization primitives
- `synchronized`, `Lock`, `volatile`, and `ThreadLocal`
- Thread pools and queue strategies
- JUC tools such as `CountDownLatch`, `Semaphore`, and `CyclicBarrier`
- Topic notes for `BlockingQueue`, `Callable` / `FutureTask`, `ForkJoin`, and JUC

## Start here

- Chinese home: [README.zh-CN.md](README.zh-CN.md)
- Docs index: [docs/README.md](docs/README.md)

## Topic map

| Topic | Summary | Document |
| --- | --- | --- |
| Multithreading overview | Thread lifecycle, communication, locks, pools, and JUC basics | [README.zh-CN.md](README.zh-CN.md) |
| BlockingQueue | Concepts, categories, and common APIs | [docs/BlockingQueue.md](docs/BlockingQueue.md) |
| Callable and FutureTask | Differences from `Runnable`, result handling, async usage | [docs/Callable%20FutureTask.md](docs/Callable%20FutureTask.md) |
| ForkJoin | Divide-and-conquer model, core classes, example | [docs/ForkJoin.md](docs/ForkJoin.md) |
| JUC overview | Process vs thread, states, and concurrency utilities | [docs/JUC.md](docs/JUC.md) |
| Redis batch data script | Helper note for generating Redis test data | [docs/batchRedis.md](docs/batchRedis.md) |

## Repository structure

```text
java-multithreading/
|-- README.md
|-- README.zh-CN.md
`-- docs/
    |-- README.md
    |-- BlockingQueue.md
    |-- Callable FutureTask.md
    |-- ForkJoin.md
    |-- JUC.md
    `-- batchRedis.md
```

## Language status

- `README.md`: English overview
- `README.zh-CN.md`: Chinese main notes
- `docs/README.md`: Bilingual docs index
- Topic files under `docs/`: currently Chinese content with original filenames retained

## Suggested reading order

1. Read [README.md](README.md) or [README.zh-CN.md](README.zh-CN.md) for the overall map.
2. Continue with [docs/JUC.md](docs/JUC.md) for core concurrency context.
3. Read [docs/BlockingQueue.md](docs/BlockingQueue.md), [docs/Callable%20FutureTask.md](docs/Callable%20FutureTask.md), and [docs/ForkJoin.md](docs/ForkJoin.md) by topic.
4. Use [docs/batchRedis.md](docs/batchRedis.md) only if you need the Redis data generation snippet.

## Notes

- The repository is note-oriented rather than code-heavy.
- The current bilingual support focuses on navigation, overview, and topic discovery.
- Detailed topic translation can be added later without changing the current document structure.
