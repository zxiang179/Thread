package zx.concurrent;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {
	
	public static void main(String[] args) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					System.out.println("�����߳�1"+"�ȴ������̴߳������...");
					countDownLatch.await();
					System.out.println("t1�̼߳���ִ��...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					System.out.println("t2�̳߳�ʼ������...");
					Thread.sleep(3000);
					System.out.println("t2�̳߳�ʼ����ϣ�֪ͨt1�̼߳���...");
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		},"t2");
		
		Thread t3 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					System.out.println("t3�߳̽��г�ʼ������...");
					Thread.sleep(4000);
					System.out.println("t3�̳߳�ʼ����ϣ�֪ͨt1�̼߳���...");
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t3");
		t1.start();
		t2.start();
		t3.start();
	}

}
