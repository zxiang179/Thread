package zx.design.masterworker;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		Master master = new Master(new Worker(),10);
		
		Random r = new Random();
		for(int i=0;i<100;i++){
			Task task = new Task();
			task.setId(i);
			task.setName("����"+i);
			task.setPrice(r.nextInt(1000));
			master.submit(task);
		}
		master.execute();
		
		long start = System.currentTimeMillis();
		while(true){
			if(master.isComplete()){
				long end = System.currentTimeMillis() -start;
				int ret = master.getResult();
				System.out.println("���ս����"+ret+",ִ�к�ʱ��"+end+"����");
				break;
			}
		}
	}

}
