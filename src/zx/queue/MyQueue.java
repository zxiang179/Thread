package zx.queue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
	
	//1.需要一个集合承装元素
	private final LinkedList<Object> list = new LinkedList<Object>();
	
	//2.需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	
	//3.需要指定上限和下限
	private int minSize=0;
	private int maxSize;
	
	//4.构造方法
	public MyQueue(int size){
		this.maxSize=size;
	}
	
	//5.初始化一个对象用于加锁
	private final Object lock = new Object();
	
	//put
	public void put(Object obj){
		synchronized (lock) {
			while(count.get()==this.maxSize){
				//不能再添加元素
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//1 加入元素
			list.add(obj);
			//2 计数器累加
			count.incrementAndGet();
			System.out.println("新加入的元素："+obj);
			//3 通知另外一个线程(唤醒)
			lock.notify();
		}
		
	}
	
	//take
	public Object take(){
		synchronized (lock) {
			Object ret=null;
			while(count.get()==this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//1 移除元素操作
			ret = list.removeFirst();
			//2 计数器递减
			count.decrementAndGet();
			//3 唤醒另外一个线程
			lock.notify();
			
			return ret;
		}
	}
	
	public int getSize(){
		return this.count.get();
	}
	
	public static void main(String[] args) {
		final MyQueue myQueue = new MyQueue(5);
		myQueue.put("a");
		myQueue.put("b");
		myQueue.put("c");
		myQueue.put("d");
		myQueue.put("e");
		
		System.out.println("当前容器的长度："+myQueue.getSize());
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				myQueue.put("f");
				myQueue.put("g");
			}
		},"t1");
		
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				Object o1 = myQueue.take();
				System.out.println("移除的元素为："+o1);
				Object o2 = myQueue.take();
				System.out.println("移除的元素为："+o2);
			}
		},"t2");
		

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}
}

