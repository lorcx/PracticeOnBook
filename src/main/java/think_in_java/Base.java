package think_in_java;
/**
 * 匿名内部类
 */
public abstract class Base {
	public abstract void f();
	
	public Base(int i ){
		System.out.println("base class :"+i);
	}
}

/**
 * Anonymous 匿名的
 * output:
 *    base class :3
	  Anoymous .... class :
 *
 */
class AnonymousConstructor{
	public static Base getBase(int i){
		return new Base(i) {

			public void f() {
				System.out.println("Anoymous .... class :" );
			}
		};
	}
	
	public static void main(String[] args) {
		Base base = new AnonymousConstructor().getBase(3);
		base.f();
	}
	
}

