package zx.test5;

/**
 * 同步synchronized的异常处理
 * 适用于处理多个任务，要求同时成功或者同时失败
 * @author Carl_Hugo
 *
 */
public class SyncException {
	
	private int i=0;
	public synchronized void operation(){
		while(true){
			try{
				i++;
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName()+" ,i="+i);
				if(i==10){
					Integer.parseInt("a");
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(" log info i = "+i);
				throw new RuntimeException();
			}
		}
	}
	
	public static void main(String[] args){
		final SyncException se=new SyncException();
		Thread t1 =new Thread(new Runnable(){
			@Override
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();
	}
}
