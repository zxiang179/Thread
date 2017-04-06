package zx.test7;
/**
 * 使用字符串常量加锁会导致死循环问题 
 * @author Carl_Hugo
 */
public class StringLock {

	public void method(){
		synchronized ("字符串常量") {
			try{
				while(true){
					System.out.println("当前线程："+Thread.currentThread().getName()+"开始");
					Thread.sleep(1000);
					System.out.println("当前线程："+Thread.currentThread().getName()+"结束");
				} 
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final StringLock stringlock = new StringLock();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				stringlock.method();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				stringlock.method();
			}
		},"t2");
		t1.start();
		t2.start();
	}
	
}
