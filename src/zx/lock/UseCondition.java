package zx.lock;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseCondition {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("��ǰ�߳�:"+Thread.currentThread().getName()+"����ȴ�״̬..");
			Thread.sleep(3000);
			System.out.println("��ǰ�߳�:"+Thread.currentThread().getName()+"�ͷ���..");
			condition.await();//object wait ���ͷ���
			System.out.println("��ǰ�߳�:"+Thread.currentThread().getName()+"����ִ��..");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("��ǰ�߳�:"+Thread.currentThread().getName()+"����..");
			Thread.sleep(3000);
			System.out.println("��ǰ�߳�:"+Thread.currentThread().getName()+"��������..");
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		CopyOnWriteArrayList a = new CopyOnWriteArrayList();
		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				uc.method1();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				uc.method2();
			}
		},"t2");
		t1.start();
		t2.start();
	}

}
