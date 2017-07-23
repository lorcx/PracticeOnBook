package think_in_java;
/**
 * 局部内部类
 * @author dell
 *
 */
public class Parcel5 {
	private final int num = 5;
	public Contents doSome(){
		class InnerDo implements Contents {
			public InnerDo() {
				System.out.println("方法中的内部类1!");
			}

			public void f() {
				System.out.println("ffffff");
			}
		}
		System.out.println("doSome.......");
		return new InnerDo();
	}
	
	public static void main(String[] args) {
		Parcel5 p = new Parcel5();
		Contents c = p.doSome();//方法中的内部类创建时，直接调用这个方法就行
		c.f();
	}
}
