package zx.test7;
/**
 * ���������Եı仯����Ӱ�����ı仯
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
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+" ��ʼ");
			this.setAge(age);
			this.setName(name);
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+"�޸Ķ�������Ϊ��"
					+this.getName()+", "+this.getAge());
			Thread.sleep(2000);
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+" ����");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		final ModifyLock modifyLock = new ModifyLock();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				modifyLock.changAttribute("����", 20);
			}
		},"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				modifyLock.changAttribute("����", 21);
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
