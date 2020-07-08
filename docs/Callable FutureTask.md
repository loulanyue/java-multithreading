##### 一、获得多线程的方式
	
	1.1 继承thread类
	1.2 实现runnable接口，
	1.3 java5以后又有实现callable接口
	1.4 java的线程池获得

##### 二、callable接口与runnable接口的区别
 
	2.1 返回值
	2.2 抛异常
	2.3 落地方法，一个是run，一个是call
	 
	 
	 
##### 三、FutureTask是什么
		简言之，FutureTask是未来的任务，用它就干一件事，异步调用
		main方法就像一个冰糖葫芦，一个个方法由main串起来
		但解决不了一个问题：正常调用挂起堵塞问题

##### 四、FutureTask原理：
在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态
 
一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果
 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，然后会返回结果或者抛出异常
 
	只计算一次
	get方法放到最后

##### 五、实现示例：

	
	import java.util.concurrent.Callable;
	import java.util.concurrent.FutureTask;
	import java.util.concurrent.TimeUnit;
	
	class MyThread implements Runnable{
	
	    @Override
	    public void run() {
	
	    }
	}
	class MyThread2 implements Callable<Integer> {
	
	    @Override
	    public Integer call() throws Exception {
	        System.out.println(Thread.currentThread().getName()+"come in callable");
	        return 200;
	    }
	}
	
	
	public class CallableDemo {
	
	
	    public static void main(String[] args) throws Exception {
	
	        //FutureTask<Integer> futureTask = new FutureTask(new MyThread2());
	        FutureTask<Integer> futureTask = new FutureTask(()->{
	            System.out.println(Thread.currentThread().getName()+"  come in callable");
	            TimeUnit.SECONDS.sleep(4);
	            return 1024;
	        });
	        FutureTask<Integer> futureTask2 = new FutureTask(()->{
	            System.out.println(Thread.currentThread().getName()+"  come in callable");
	            TimeUnit.SECONDS.sleep(4);
	            return 2048;
	        });
	
	        new Thread(futureTask,"zhang3").start();
	        new Thread(futureTask2,"li4").start();
	
	        //System.out.println(futureTask.get());
	        //System.out.println(futureTask2.get());
	        //1、一般放在程序后面，直接获取结果
	        //2、只会计算结果一次
	
	        while(!futureTask.isDone()){
	            System.out.println("***wait");
	        }
	        System.out.println(futureTask.get());
	        System.out.println(Thread.currentThread().getName()+" come over");
	    }
	}
