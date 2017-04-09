package zx.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class UseExecutors {
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		ExecutorService pool2 = Executors.newSingleThreadExecutor();
		ExecutorService pool3 = Executors.newCachedThreadPool();
		ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(10);
	}

}
