package zx.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{
	private String para;
	
	public UseFuture(String para){
		this.para=para;
	}

	/**
	 * ��ʵ��ҵ���߼�����ִ�п��ܺ���
	 */
	@Override
	public String call() throws Exception {
		//ģ��ִ�к�ʱ
		Thread.sleep(3000);
		String result = this.para+"�������";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String queryStr = "query";
		//����FutureTask�����Ҵ�����Ҫ��������ҵ������࣬����һ����ʵ����Callable�ӿڵ���
		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
		//����һ���̶��̵߳��̳߳����߳���Ϊ1
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//�����ύ����future�������߳�ִ��RealData��call()����ִ��
		//submit��executor�����𣺵�һ�� submit���Դ���ʵ��callable�ӿڵ�ʵ������ �ڶ��� submit�����з���ֵ
		Future f = executor.submit(future);
		System.out.println("�������");
		//������ֵΪnull ��ʾִ�н���
		/*System.out.println(f.get());
		while(true){
			if(f.get()==null){
				System.out.println("��ǰ�߳�ִ�����...");
				break;
			}
		}*/
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//���û�ȡ���ݷ��������call()����û��ִ����ɣ������ɻ�ȴ�,get()�����첽��ȡִ�н��
		System.out.println("����:"+future.get());
		executor.shutdown();
		
	}
	
	

}
