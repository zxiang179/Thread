package zx.test7;
/**
 * ʹ���ַ������������ᵼ����ѭ������ 
 * @author Carl_Hugo
 */
public class StringLock {

	public void method(){
		synchronized ("�ַ�������") {
			try{
				while(true){
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"��ʼ");
					Thread.sleep(1000);
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"����");
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
