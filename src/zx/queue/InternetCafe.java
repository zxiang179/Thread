package zx.queue;

import java.util.concurrent.DelayQueue;

public class InternetCafe implements Runnable{

	private static DelayQueue<InternetUsers> queue = new DelayQueue<InternetUsers>();
	public boolean  onBusiness = true;
	
	public void shangji(String name,String id,int money){
		InternetUsers man = new InternetUsers(name,id,1000*money+System.currentTimeMillis());
		System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块，开始上机...");
		this.queue.add(man);
	}
	
	public void xiaji(InternetUsers man){
		System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("网吧开始营业");
			InternetCafe wb = new InternetCafe();
			Thread shangwang = new Thread(wb);
			shangwang.start();
			
			wb.shangji("路人甲", "123", 1);
			wb.shangji("路人乙", "234", 10);
			wb.shangji("路人丙", "345", 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(onBusiness){
			try {
				//当执行take操作时候并不直接获取对象，等到延迟时间到了才拿出对象
				InternetUsers man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
