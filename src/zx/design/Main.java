package zx.design;

public class Main {
	
	public static void main(String[] args) {
		FutureClient fc = new FutureClient();
		Data data = fc.request("�������");
		System.out.println("�����ͳɹ���");
		System.out.println("������������...");
		
		String result = data.getRequest();
		System.out.println(result);
		
	}

}
