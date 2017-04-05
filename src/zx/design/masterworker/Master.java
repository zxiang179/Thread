package zx.design.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	//1 Ӧ����һ����װ����ļ���
	private ConcurrentLinkedQueue<Task> workQueue = new  ConcurrentLinkedQueue<Task>();
	
	//2 ʹ����ͨ��HashMapȥ��װ���е�worker����
	private HashMap<String,Thread> workers = new HashMap<String,Thread>();
	
	//3 ʹ��һ��������װÿһ��worker����ִ�еĽ����
	private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String,Object>();
	
	//4 ���췽��
	public Master(Worker worker,int workerCount){
		//ÿһ��worker������Ҫ��master��Ӧ��workQueue�����������ȡ��resultMap����������ύ
		worker.setWorkerQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		
		for(int i=0;i<workerCount;i++){
			//key��ʾÿһ��worker������,value��ʾ�߳�ִ�ж���
			workers.put("�ӽڵ�"+Integer.toString(i), new Thread(worker));
		}
	}
	
	//5 �ύ����
	public void submit(Task task){
		this.workQueue.add(task);
	}
	
	//6 ��Ҫ��һ��ִ�еķ���(����Ӧ�ó��������е�worker����)
	public void execute(){
		for(Map.Entry<String,Thread> me:workers.entrySet()){
			me.getValue().start();
		}
	}

	//8 �ж��߳��Ƿ�ִ�����
	public boolean isComplete() {
		for(Map.Entry<String, Thread> me:workers.entrySet()){
			if(me.getValue().getState()!=Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}

	//9 ���ؽ��������
	public int getResult() {
		int ret = 0;
		for(Map.Entry<String,Object> me:resultMap.entrySet()){
			//�����߼�
			ret+=(Integer)me.getValue();
		}
		return ret;
	}
	
	
}
