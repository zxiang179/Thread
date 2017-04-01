package zx.test7;
/**
 * �������޸�
 * @author Carl_Hugo
 */
public class ChangeLock {
	
	private String lock = "lock";
	
	private void method(){
		synchronized (lock) {
			try{
				System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��ʼ");
				lock="change lock";
				Thread.sleep(2000);
				System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����");
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final ChangeLock changelock = new ChangeLock();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				changelock.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				changelock.method();
			}
		},"t2");
		t1.start();
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		t2.start();
	}

}
