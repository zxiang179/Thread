package zx.communicate;

import java.util.ArrayList;
import java.util.List;

public class ListAdd2 {
	
	private volatile static List list = new ArrayList();
	
	public void add(){
		list.add("bjsxt");
	}
	
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd2 list2 = new ListAdd2();
		
		//实例化出来一个lock
		//当使用wait和notify的时候，一定要配合synchronized关键字去使用
		//创建一个对象，用这个对象当成一把锁
		final Object lock = new Object();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for(int i=0;i<10;i++){
							list2.add();
							System.out.println("当前线程："+Thread.currentThread().getName()+" 添加了一个元素");
							Thread.sleep(500);
							if(list.size()==5){
								System.out.println("已经发出通知");
								lock.notify();
							}
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
				synchronized (lock) {
					if(list.size()!=5){
						try {
							//wait方法释放锁 notify方法不释放锁
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//size等于5的情况
					System.out.println("当前线程："+Thread.currentThread().getName()+"收到通知线程停止...");
					throw new RuntimeException();
				}
			}
		},"t2");
		t2.start();
		t1.start();
	}
}
