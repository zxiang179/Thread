package zx.design.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String,Object> resultMap;
	
	@Override
	public void run() {
		while(true){
			Task input = this.workQueue.poll();
			if(input==null)break;
			//������ȥ��ҵ����
			Object output = handle(input);
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}

	public Object handle(Task input){
		return null;
	}
	


	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue=workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap=resultMap;
		
	}

}
