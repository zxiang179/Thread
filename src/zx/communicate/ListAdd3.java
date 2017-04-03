package zx.communicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd3 {
	
	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("bjsxt");
	}
	
	public int size(){
		return list.size();
	}
	
	//实现实时的通知效果
	static final CountDownLatch countDownLatch = new CountDownLatch(2);
	
	public static void main(String[] args) {
		
		final ListAdd3 list2 = new ListAdd3();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
						for(int i=0;i<10;i++){
							list2.add();
							System.out.println("当前线程："+Thread.currentThread().getName()+" 添加了一个元素");
							Thread.sleep(500);
							if(list.size()==5){
								System.out.println("已经发出通知");
								countDownLatch.countDown();
								countDownLatch.countDown();
							}
						}	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				//wait 和 notify方法必须与synchronized方法配合使用
					if(list.size()!=5){
						try {
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//size等于5的情况
					System.out.println("当前线程："+Thread.currentThread().getName()+"收到通知，线程停止...");
					throw new RuntimeException();
			}
		},"t2");
		t1.start();
		t2.start();
	}
	
	
}
