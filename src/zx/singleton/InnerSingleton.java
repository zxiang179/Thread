package zx.singleton;

public class InnerSingleton {
	
	//��̬�ڲ��࣬ʵ��������
	private static class Singleton{
		private static Singleton single = new Singleton();
	}
	
	private static Singleton getInstance(){
		return Singleton.single;
	}

}
