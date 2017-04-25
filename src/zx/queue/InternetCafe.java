package zx.queue;

import java.util.concurrent.DelayQueue;

public class InternetCafe implements Runnable{

	private static DelayQueue<InternetUsers> queue = new DelayQueue<InternetUsers>();
	public boolean  onBusiness = true;
	
	public void shangji(String name,String id,int money){
		InternetUsers man = new InternetUsers(name,id,1000*money+System.currentTimeMillis());
		System.out.println("����"+man.getName()+" ���֤"+man.getId()+"��Ǯ"+money+"�飬��ʼ�ϻ�...");
		this.queue.add(man);
	}
	
	public void xiaji(InternetUsers man){
		System.out.println("����"+man.getName()+" ���֤"+man.getId()+"ʱ�䵽�»�...");
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("���ɿ�ʼӪҵ");
			InternetCafe wb = new InternetCafe();
			Thread shangwang = new Thread(wb);
			shangwang.start();
			
			wb.shangji("·�˼�", "123", 1);
			wb.shangji("·����", "234", 10);
			wb.shangji("·�˱�", "345", 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(onBusiness){
			try {
				//��ִ��take����ʱ�򲢲�ֱ�ӻ�ȡ���󣬵ȵ��ӳ�ʱ�䵽�˲��ó�����
				InternetUsers man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
