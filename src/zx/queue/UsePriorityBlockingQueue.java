package zx.queue;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
	
	public static void main(String[] args) {
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
		
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("任务1");
		Task t2 = new Task();
		t2.setId(6);
		t2.setName("任务2");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("任务3");
		Task t4 = new Task();
		t4.setId(9);
		t4.setName("任务4");
		
		q.add(t1);
		q.add(t2);
		q.add(t3);
		q.add(t4);
		
		System.out.println(q);
		for(Task t:q){
			try {
				System.out.println(q.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("result:"+q);
	}
	

}
