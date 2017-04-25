package zx.test1;
/**
 * 多个线程单个锁
 * @author Carl_Hugo
 *
 */
public class MyThread extends Thread{
	
	private int count=5;
	
	@Override
	public synchronized void run() {
		try {
			count--;
			System.out.println(this.currentThread().getName()+" count="+count);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread myThread = new MyThread();
		Thread t1 = new Thread(myThread,"t1");
		Thread t2 = new Thread(myThread,"t2");
		Thread t3 = new Thread(myThread,"t3");
		Thread t4 = new Thread(myThread,"t4");
		Thread t5 = new Thread(myThread,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

}
