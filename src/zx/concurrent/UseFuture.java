package zx.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{
	private String para;
	
	public UseFuture(String para){
		this.para=para;
	}

	/**
	 * 真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		//模拟执行耗时
		Thread.sleep(3000);
		String result = this.para+"处理完成";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String queryStr = "query";
		//构建FutureTask，并且传入需要真正进行业务处理的类，该类一定是实现了Callable接口的类
		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
		//创建一个固定线程的线程池且线程数为1
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//这里提交任务future，则开启线程执行RealData的call()方法执行
		//submit和executor的区别：第一点 submit可以传入实现callable接口的实力对象 第二点 submit方法有返回值
		Future f = executor.submit(future);
		System.out.println("请求完毕");
		//当返回值为null 表示执行结束
		/*System.out.println(f.get());
		while(true){
			if(f.get()==null){
				System.out.println("当前线程执行完毕...");
				break;
			}
		}*/
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//调用获取数据方法，如果call()方法没有执行完成，则依旧会等待,get()方法异步获取执行结果
		System.out.println("数据:"+future.get());
		executor.shutdown();
		
	}
	
	

}
