package zx.design.producerandconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable{

	//��������
	private BlockingQueue<Data> queue;
	//���̼߳��Ƿ�������������ǿ�ƴ����߳���ˢ�µĹ��ܣ���ʱ�����̵߳�״̬
	private volatile boolean isRunning = true;
	//id������
	private static AtomicInteger count = new AtomicInteger();
	//�������
	private static Random r = new Random();
	
	public Provider(BlockingQueue queue){
		this.queue=queue;
	}
	
	@Override
	public void run() {
		while(isRunning){
			try {
				//�������0-1000���� ��ʾ��ȡ����(�������ݵĺ�ʱ)
				Thread.sleep(r.nextInt(1000));
				//��ȡ�����ݽ����ۼ�
				int id = count.incrementAndGet();
				//����ͨ��һ��getData������ȡ��
				Data data = new Data(Integer.toString(id),"����"+id);
				System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+",��ȡ�����ݣ�idΪ��"+id+",����װ�ص�������������...");
				if(!this.queue.offer(data,2,TimeUnit.SECONDS)){
					System.out.println("�ύ����������ʧ��");
					//do something..���������ύ
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		this.isRunning=false;
	}

}
