package zx.concurrentcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

public class Tickets {

	public static void main(String[] args) {
//		List<String> tickets = new ArrayList<String>();
		//��ʼ����Ʊ�ز���ӻ�Ʊ�������߳�ͬ���ɲ�ȡvector���ArrayList HashTable���HashMap
		final Vector<String> tickets = new Vector<String>();
		//������ת��Ϊ�̰߳�ȫ��
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<String,String>());
		
		for(int i=1;i<=1000;i++){
			tickets.add("��Ʊ"+i);
		}
		
		for(int i=1;i<=10;i++){
			new Thread("�߳�"+i){
				public void run(){
					while(true){
						if(tickets.isEmpty())break;
						System.out.println(Thread.currentThread().getName()+"---"+tickets.remove(0));
					}
				}
			}.start();
		}
		
	}
}
