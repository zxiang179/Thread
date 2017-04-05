package zx.queue;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable{

	private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();
	public boolean yinye = true;
	
	public void shangji(String name,String id,int money){
		Wangmin man = new Wangmin(name,id,1000*money+System.currentTimeMillis());
		System.out.println("����"+man.getName()+" ���֤"+man.getId()+"��Ǯ"+money+"�飬��ʼ�ϻ�...");
		this.queue.add(man);
	}
	
	public void xiaji(Wangmin man){
		System.out.println("����"+man.getName()+" ���֤"+man.getId()+"ʱ�䵽�»�...");
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("���ɿ�ʼӪҵ");
			WangBa siyu = new WangBa();
			Thread shangwang = new Thread(siyu);
			shangwang.start();
			
			siyu.shangji("·�˼�", "123", 1);
			siyu.shangji("·����", "234", 10);
			siyu.shangji("·�˱�", "345", 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(yinye){
			try {
				//��ִ��take����ʱ�򲢲�ֱ�ӻ�ȡ���󣬵ȵ��ӳ�ʱ�䵽�˲��ó�����
				Wangmin man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
