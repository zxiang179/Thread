package zx.design.masterworker;

public class MyWorker extends Worker{
	
	public static Object handle(Task input) {
		Object output = null;
		try {
			//����task�ĺ�ʱ�����������ݵļӹ���Ҳ�����ǲ������ݿ�
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}

}
