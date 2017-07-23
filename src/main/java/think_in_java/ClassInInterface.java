package think_in_java;
/**
 * 接口中的内部类
 * @author dell
 *
 */
public interface ClassInInterface {
	void howdy();
	
	class Test implements ClassInInterface{

		public void howdy() {
			System.out.println("howdy");
		}
	
		public static void main(String[] args) {
			new Test().howdy();
		}
	}
}
