package zx.design;

public class FutureData implements Data{
	
	private RealData realData;
	
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData realData){
		//����Ѿ�������Ͼ�ֱ�ӷ���
		if(isReady){
			return;
		}
		//���û��װ�أ�����װ����ʵ����
		this.realData= realData;
		isReady = true;
		//����֪ͨ
		notify();
	}

	@Override
	public synchronized String getRequest() {
		//���û��װ�غã������һֱ��������״̬
		while(!isReady){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//װ�غ�ֱ�ӻ�ȡ���ݼ���
		return this.realData.getRequest();
	}

}
