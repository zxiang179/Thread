package zx.design.masterworker;

public class MyWorker extends Worker{
	
	public static Object handle(Task input) {
		Object output = null;
		try {
			//处理task的耗时，可能是数据的加工，也可能是操作数据库
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}

}
