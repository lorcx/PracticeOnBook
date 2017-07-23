package think_in_java;
/**
 * 接口与工厂(协变类型)
 * @author dell
 *
 */
public interface Services {
	void method1();
	void method2();
}

interface ServiceFactory{
	Services getService();
}

class Impl1 implements Services{
	public void method1() {
		 
	}
	@Override
	public void method2() {
		
	}
}

class Impl2 implements Services{
	public void method1() {
		 
	}
	@Override
	public void method2() {
		
	}
}


class ImplFactory1 implements ServiceFactory{

	public Services getService() {
		return new Impl1();
	}
}

class ImplFactory2 implements ServiceFactory{

	public Services getService() {
		return new Impl2();
	}
}

class Factories{
	public static void serviceConsumer(ServiceFactory sf){
		Services s = sf.getService();
		s.method1();
		s.method2();
	}
	
	public static void main(String[] args) {
		serviceConsumer(new ImplFactory1());
		serviceConsumer(new ImplFactory2());
		
	}
}




