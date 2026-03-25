# Redis Batch Test Data

**Language:** English + 中文

## English Summary

This short note shows how to generate a large Redis test data file in Linux, clean Windows-style line endings with Vim, and then import the commands into Redis with `redis-cli --pipe`.

## 中文正文

## 一、批量生成 Redis 测试数据

### 1. 在 Linux Bash 下面执行

```bash
for((i=1;i<=20000000;i++)); do echo "set k$i v$i" >> /tmp/redisTest.txt ;done;
```

生成 2000 万条 Redis 批量设置 KV 的语句（`key=kn`，`value=vn`），写入到 `/tmp/redisTest.txt` 文件中。

### 2. 用 Vim 去掉行尾的 `^M` 符号

```vim
vim /tmp/redisTest.txt
:set fileformat=dos
:wq
```

可以通过设置文件格式的方式去掉每行结尾的 `^M` 符号，然后保存退出。

### 3. 使用 Redis 的 `--pipe` 方式批量导入

```bash
cat /tmp/redisTest.txt | 路径/redis-5.0.0/src/redis-cli -h 主机ip -p 端口号 --pipe
```

通过 `redis-cli --pipe` 可以把文件里的指令批量灌入 Redis，通常需要几分钟到十分钟左右，具体取决于机器和数据量。
