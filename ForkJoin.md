##### 一、什么是ForkJoin

大数据里面有map reduce ，Java从JDK1.7开始借鉴了这种分而治之的思想，提供ForkJoin框架用于并行执行任务，它的思想就是讲一个大任务分割成若干小任务，最终汇总每个小任务的结果得到这个大任务的结果

Fork：把一个复杂任务进行分拆，大事化小
Join：把分拆任务的结果进行合并


##### 二、3大类
**2.1 ForkJoinPool**
分支合并池    类比=>   线程池
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020042221294435.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)
**2.2 ForkJoinTask**
ForkJoinTask    类比=>   FutureTask
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200422213025136.png)
**2.3 RecursiveTask**
递归任务：继承后可以实现递归(自己调自己)调用的任务
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200422213043897.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdWxhbnl1ZV8=,size_16,color_FFFFFF,t_70)
##### 三、使用示例

	
	import java.util.concurrent.ExecutionException;
	import java.util.concurrent.ForkJoinPool;
	import java.util.concurrent.ForkJoinTask;
	import java.util.concurrent.RecursiveTask;
	
	class MyTask extends RecursiveTask<Integer>{
	    private static final Integer ADJUST_VALUE = 10;
	    private int begin;
	    private int end;
	    private int result;
	
	    public MyTask(int begin, int end) {
	        this.begin = begin;
	        this.end = end;
	    }
	
	    @Override
	    protected Integer compute() {
	        if((end - begin)<=ADJUST_VALUE){
	            for(int i =begin;i <= end;i++){
	                result = result + i;
	            }
	        }else{
	            int middle = (begin + end)/2;
	            MyTask task01 = new MyTask(begin,middle);
	            MyTask task02 = new MyTask(middle+1,end);
	            task01.fork();
	            task02.fork();
	            result =  task01.join() + task02.join();
	        }
	
	
	        return result;
	    }
	}
	
	
	/**
	 * 分支合并例子
	 * ForkJoinPool
	 * ForkJoinTask
	 * RecursiveTask
	 */
	public class ForkJoinDemo {
	
	    public static void main(String[] args) throws ExecutionException, InterruptedException {
	
	        MyTask myTask = new MyTask(0,100);
	        ForkJoinPool forkJoinPool = new ForkJoinPool();
	        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
	
	        System.out.println(forkJoinTask.get());
	
	        forkJoinPool.shutdown();
	    }
	}
