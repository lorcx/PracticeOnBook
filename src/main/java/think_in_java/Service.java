package think_in_java;
/**
 * 使用内部类对工厂进行改进
 * @author dell
 *
 */
public interface Service {
	public void method1();
	public void method2();
}

interface ServiceFactory1{
	public Service getService();
}

class serviceImpl1 implements Service{
	public void method1() {
		System.out.println("ser impl1 method1");
	}
	public void method2() {
		System.out.println("ser impl1 method1");
	}
	public static ServiceFactory1 sf1 = new ServiceFactory1() {
		public Service getService() {
			return new serviceImpl1();
		}
	};
}

class serviceImpl2 implements Service{
	public void method1() {
		System.out.println("ser impl2 method2");
	}
	public void method2() {
		System.out.println("ser impl2 method2");
	}
	
	public static ServiceFactory1 sf2 = new ServiceFactory1() {
		public Service getService() {
			return new serviceImpl2();
		}
	};
}


class Factors{
	public static void serviceConsumer(ServiceFactory1 sf){
		Service ser = sf.getService();
		ser.method1();
		ser.method2();
	}
	
	public static void main(String[] args) {
		serviceConsumer(serviceImpl1.sf1);
		serviceConsumer(serviceImpl2.sf2);
	}
}
