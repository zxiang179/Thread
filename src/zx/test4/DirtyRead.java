package zx.test4;

public class DirtyRead {
	
	private String username = "Tom";
	private String password = "123456";
	
	public synchronized void setValue(String username,String password){
		this.username=username;
		
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		this.password=password;
		
		System.out.println("setValue�����ս����username = "+username+",password = "+password);
	}
	
	public synchronized void getValue(){
		System.out.println("getValue�����õ��� username = "+this.username+" ,password = "+this.password);
	}
	
	public static void main(String[] args) throws InterruptedException {
		final DirtyRead dr = new DirtyRead();
		Thread t1=new Thread(new Runnable(){
			@Override
			public void run() {
				dr.setValue("z3", "456");
			}
		});
		t1.start();
		Thread.sleep(1000);
		dr.getValue();
	}

}
