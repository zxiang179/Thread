package zx.queue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
	
	//1.��Ҫһ�����ϳ�װԪ��
	private final LinkedList<Object> list = new LinkedList<Object>();
	
	//2.��Ҫһ��������
	private AtomicInteger count = new AtomicInteger(0);
	
	//3.��Ҫָ�����޺�����
	private int minSize=0;
	private int maxSize;
	
	//4.���췽��
	public MyQueue(int size){
		this.maxSize=size;
	}
	
	//5.��ʼ��һ���������ڼ���
	private final Object lock = new Object();
	
	//put
	public void put(Object obj){
		synchronized (lock) {
			while(count.get()==this.maxSize){
				//���������Ԫ��
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//1 ����Ԫ��
			list.add(obj);
			//2 �������ۼ�
			count.incrementAndGet();
			System.out.println("�¼����Ԫ�أ�"+obj);
			//3 ֪ͨ����һ���߳�(����)
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
			//1 �Ƴ�Ԫ�ز���
			ret = list.removeFirst();
			//2 �������ݼ�
			count.decrementAndGet();
			//3 ��������һ���߳�
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
		
		System.out.println("��ǰ�����ĳ��ȣ�"+myQueue.getSize());
		
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
				System.out.println("�Ƴ���Ԫ��Ϊ��"+o1);
				Object o2 = myQueue.take();
				System.out.println("�Ƴ���Ԫ��Ϊ��"+o2);
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

