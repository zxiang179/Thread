package zx.design.producerandconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private BlockingQueue<Data> queue;
    
    public Consumer(BlockingQueue queue){
    	this.queue=queue;
    }
    
    //�������
    private static Random r = new Random();
	
	@Override
	public void run() {
		while(true){
			try{
				//��ȡ����
				Data data = this.queue.take();
				//�������ݴ��� ����0-1000����ģ���ʱ
				Thread.sleep(r.nextInt(1000));
				System.out.println("��ǰ�����߳�:"+Thread.currentThread().getName()+",���ѳɹ�����������Ϊid:"+data.getId());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
