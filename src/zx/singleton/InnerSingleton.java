package zx.singleton;

public class InnerSingleton {
	
	//静态内部类，实例化对象
	private static class Singleton{
		private static Singleton single = new Singleton();
	}
	
	private static Singleton getInstance(){
		return Singleton.single;
	}

}
