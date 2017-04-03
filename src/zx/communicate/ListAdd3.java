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
	
	//ʵ��ʵʱ��֪ͨЧ��
	static final CountDownLatch countDownLatch = new CountDownLatch(2);
	
	public static void main(String[] args) {
		
		final ListAdd3 list2 = new ListAdd3();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
						for(int i=0;i<10;i++){
							list2.add();
							System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+" �����һ��Ԫ��");
							Thread.sleep(500);
							if(list.size()==5){
								System.out.println("�Ѿ�����֪ͨ");
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
				//wait �� notify����������synchronized�������ʹ��
					if(list.size()!=5){
						try {
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//size����5�����
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�յ�֪ͨ���߳�ֹͣ...");
					throw new RuntimeException();
			}
		},"t2");
		t1.start();
		t2.start();
	}
	
	
}
