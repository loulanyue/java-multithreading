##### 一、什么是阻塞队列
阻塞队列是一个队列，在数据结构中起的作用如下图：

	栈：先进后出，后进先出
	队列：先进先出
	阻塞：必须要阻塞/不得不阻塞 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200420194232932.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)
当队列是空的，从队列中获取元素的操作将会被阻塞
当队列是满的，从队列中添加元素的操作将会被阻塞
 
试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素
 
试图向已满的队列中添加新元素的线程将会被阻塞，直到其他线程从队列中移除一个或多个元素或者完全清空，使队列变得空闲起来并后续新增




##### 二、阻塞队列的作用

在多线程领域：所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤起
 
**2.1 为什么需要BlockingQueue**
好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都给你一手包办了
 
在concurrent包发布以前，在多线程环境下，我们每个程序员都必须去自己控制这些细节，尤其还要兼顾效率和线程安全，而这会给我们的程序带来不小的复杂度。


**2.2 阻塞队列的分类**

	ArrayBlockingQueue：由数组结构组成的有界阻塞队列
	LinkedBlockingQueue：由链表结构组成的有界（但大小默认值为integer.MAX_VALUE）阻塞队列
	PriorityBlockingQueue：支持优先级排序的无界阻塞队列
	DelayQueue：使用优先级队列实现的延迟无界阻塞队列
	SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列
	LinkedTransferQueue：由链表组成的无界阻塞队列
	LinkedBlockingDeque：由链表组成的双向阻塞队列

**2.3 BlockingQueue核心方法**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200420194613143.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)
| 抛出异常 | 当阻塞队列满时，再往队列里add插入元素会抛IllegalStateException:Queue full
当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException |
|特殊值|插入方法，成功ture失败false
移除方法，成功返回出队列的元素，队列里没有就返回null|
| 一直阻塞 | 当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据or响应中断退出
当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用 |
| 超时退出 | 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出 |


##### 三、使用示例
	
	import java.util.ArrayList;
	import java.util.List;
	import java.util.concurrent.ArrayBlockingQueue;
	import java.util.concurrent.BlockingQueue;
	import java.util.concurrent.TimeUnit;
	
	/**
	 * 阻塞队列
	 */
	public class BlockingQueueDemo {
	
	    public static void main(String[] args) throws InterruptedException {
	
	        //        List list = new ArrayList();
	
	        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
	        //第一组
	        //        System.out.println(blockingQueue.add("a"));
	        //        System.out.println(blockingQueue.add("b"));
	        //        System.out.println(blockingQueue.add("c"));
	        //        System.out.println(blockingQueue.element());
	
	        //System.out.println(blockingQueue.add("x"));
	        //        System.out.println(blockingQueue.remove());
	        //        System.out.println(blockingQueue.remove());
	        //        System.out.println(blockingQueue.remove());
	        //        System.out.println(blockingQueue.remove());
	        //    第二组
	        //        System.out.println(blockingQueue.offer("a"));
	        //        System.out.println(blockingQueue.offer("b"));
	        //        System.out.println(blockingQueue.offer("c"));
	        //        System.out.println(blockingQueue.offer("x"));
	        //        System.out.println(blockingQueue.poll());
	        //        System.out.println(blockingQueue.poll());
	        //        System.out.println(blockingQueue.poll());
	        //        System.out.println(blockingQueue.poll());
	        //    第三组
	        //         blockingQueue.put("a");
	        //         blockingQueue.put("b");
	        //         blockingQueue.put("c");
	        //         //blockingQueue.put("x");
	        //        System.out.println(blockingQueue.take());
	        //        System.out.println(blockingQueue.take());
	        //        System.out.println(blockingQueue.take());
	        //        System.out.println(blockingQueue.take());
	
	        //    第四组
	        System.out.println(blockingQueue.offer("a"));
	        System.out.println(blockingQueue.offer("b"));
	        System.out.println(blockingQueue.offer("c"));
	        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));
	
	    }
	}
