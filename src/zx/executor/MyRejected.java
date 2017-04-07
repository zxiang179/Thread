package zx.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler {

	public MyRejected(){
		
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理");
		System.out.println("当前被拒绝的任务为:"+r.toString());
		
	}

}
