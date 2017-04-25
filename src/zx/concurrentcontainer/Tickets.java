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
		//初始化火车票池并添加火车票：避免线程同步可采取vector替代ArrayList HashTable替代HashMap
		final Vector<String> tickets = new Vector<String>();
		//将集合转换为线程安全的
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<String,String>());
		
		for(int i=1;i<=1000;i++){
			tickets.add("火车票"+i);
		}
		
		for(int i=1;i<=10;i++){
			new Thread("线程"+i){
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
