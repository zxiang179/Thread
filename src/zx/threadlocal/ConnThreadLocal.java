package zx.threadlocal;

import java.util.Vector;

public class ConnThreadLocal {
	
	public static ThreadLocal<String> th = new ThreadLocal<String>();
	
	public void setTh(String value){
		th.set(value);
	}
	
	public void getTh(){
		System.out.println(Thread.currentThread().getName()+":"+this.th.get());
	}
	
	public static void main(String[] args) {
		final ConnThreadLocal ct = new ConnThreadLocal();
		Vector vector = new Vector();
		vector.add("123");
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				ct.setTh("ÕÅÈý");
				ct.getTh();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					ct.getTh();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t2");
		t1.start();
		t2.start();
	}

}
