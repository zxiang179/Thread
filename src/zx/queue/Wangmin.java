package zx.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Wangmin implements Delayed{
	
    private String name;
    private String id;
    private long endTime;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    
    public Wangmin(String name,String id,long endTime){
    	this.name=name;
    	this.id=id;
    	this.endTime=endTime;
    }
    
    /**
     * 相互比较排序用
     */
    @Override
	public int compareTo(Delayed delayed) {
    	Wangmin w = (Wangmin)delayed;
		return this.getDelay(this.timeUnit)-w.getDelay(this.timeUnit)>0?1:0;
	}

    /**
     * 用来判断是否到了截至日期
     */
	@Override
	public long getDelay(TimeUnit unit) {
		return endTime - System.currentTimeMillis();
	}
	
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	

}
