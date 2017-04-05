package zx.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class UseQueue {
	
	public static void main(String[] args) throws Exception{
		
		//高性能无阻塞无界队列：ConcurrentLinkedQueue
		/**
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		System.out.println(q.poll());
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.size());
		*/
		
		//有界
		/*ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		//向队列中加入一个值 如果2秒内能添加成功则返回true否则返回false
		array.offer("a",2,TimeUnit.SECONDS);
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		//长度超过了无法添加
		System.out.println(array.offer("a",2,TimeUnit.SECONDS));*/
		
		//阻塞队列 无界
		/**
		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.offer("f");
		q.add("g");
		
//		System.out.println(q.size());
//		for(Iterator iterator=q.iterator();iterator.hasNext();){
//			String string = (String)iterator.next();
//			System.out.println(string);
//		}
		
//		List<String> list = new ArrayList<String>();
//		System.out.println(q.drainTo(list,4));
//		System.out.println(list.size());
//		for(String string:list){
//			System.out.println(string);
//		}
		*/
		
		//不能往内存中存放元素
//		SynchronousQueue<String> queue = new SynchronousQueue<String>();
//		queue.add("asdasd");
		final SynchronousQueue<String> q = new SynchronousQueue<String>();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					System.out.println(q.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				q.add("asdasd");
			}
		});
		//现有take才能add
		t1.start();
		t2.start();
	}

}
