package zx.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 使用semaphore进行限流的控制
 * @author Carl_Hugo
 */
public class UseSemaphore {
	
	public static void main(String[] args) {
		//没有容量限制的线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		//只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		//模拟20个客户端访问
		for(int index=0;index<20;index++){
			final int NO = index;
			Runnable run = new Runnable(){
				@Override
				public void run() {
					try {
						//获取许可
						semp.acquire();
						System.out.println("Accessing:"+NO);
						//模拟实际业务逻辑
						Thread.sleep((long)(Math.random()*10000));
						//访问完后释放
						semp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			};
			exec.execute(run);
		}

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exec.shutdown();
	}

}
