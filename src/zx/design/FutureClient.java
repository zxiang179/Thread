package zx.design;

public class FutureClient {
	
	public Data request(final String queryStr){
		//1 ����Ҫһ���������Data�ӿڵ�ʵ���ࣩ�ȷ��ط�������Ŀͻ��ˣ������������Ѿ����յ�����������������
		final FutureData futureData = new FutureData();
		//2 ����һ���µ��̣߳�ȥ������ʵ�����ݣ����ݸ�����������
		new Thread(new Runnable(){
			@Override
			public void run() {
				//3 ����µ��߳̿���ȥ����������ʵ����Ȼ�󴫵ݸ��������
				RealData realData = new RealData(queryStr);
				futureData.setRealData(realData);
			}
		}).start();
		//ֱ�ӷ���һ���ٵİ�װ��futureData
		return futureData;
	}
}
