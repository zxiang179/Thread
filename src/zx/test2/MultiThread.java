package zx.test2;

/**
 * 一个对象一把锁
 * 在方法上加static表示在静态的方法上加synchronized修饰表示多个对象共用一把锁
 * @author Carl_Hugo
 *
 */
public class MultiThread {
	
	private static int num =0;
	
	public static synchronized void printNum(String tag){
//	public synchronized void printNum(String tag){
		try{
			if("a".equals(tag)){
				num = 100;
				System.out.println("tag a,set num over!");
				Thread.sleep(1000);
			}else{
				num = 200;
				System.out.println("tag b,set num over!");
			}
			System.out.println("tag "+tag+",num = "+num);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//两个不同的对象
		final MultiThread m1 = new MultiThread();
		final MultiThread m2 = new MultiThread();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				m1.printNum("a");
			}
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				m2.printNum("b");
			}
		});
		
		t1.start();
		t2.start();
	}

}
