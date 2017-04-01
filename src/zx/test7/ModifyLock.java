package zx.test7;
/**
 * 对象中属性的变化不会影响锁的变化
 * @author Carl_Hugo
 */
public class ModifyLock {
	
	private int age;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public synchronized void changAttribute(String name,int age){
		try{
			System.out.println("当前线程："+Thread.currentThread().getName()+" 开始");
			this.setAge(age);
			this.setName(name);
			System.out.println("当前线程："+Thread.currentThread().getName()+"修改对象内容为："
					+this.getName()+", "+this.getAge());
			Thread.sleep(2000);
			System.out.println("当前线程："+Thread.currentThread().getName()+" 结束");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		final ModifyLock modifyLock = new ModifyLock();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				modifyLock.changAttribute("张三", 20);
			}
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				modifyLock.changAttribute("李四", 21);
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
