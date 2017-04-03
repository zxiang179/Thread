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
		
		//ʵ��������һ��lock
		//��ʹ��wait��notify��ʱ��һ��Ҫ���synchronized�ؼ���ȥʹ��
		//����һ��������������󵱳�һ����
		final Object lock = new Object();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for(int i=0;i<10;i++){
							list2.add();
							System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+" �����һ��Ԫ��");
							Thread.sleep(500);
							if(list.size()==5){
								System.out.println("�Ѿ�����֪ͨ");
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
				//wait �� notify����������synchronized�������ʹ��
				synchronized (lock) {
					if(list.size()!=5){
						try {
							//wait�����ͷ��� notify�������ͷ���
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//size����5�����
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�յ�֪ͨ�߳�ֹͣ...");
					throw new RuntimeException();
				}
			}
		},"t2");
		t2.start();
		t1.start();
	}
}
