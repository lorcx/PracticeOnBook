package think_in_java;
/**
 * 内部类向上转型
 * @author dell
 *
 */
public interface Contents {
	void f();
}

/**
 * 别的类用到这个方法
 * @author dell
 *
 */
class ContentsImpl implements Contents{
	public void f() {
		System.out.println("sssssss");
	}
	
}

class Pcontents{
	public class Inner implements Contents{
		public void f() {
			System.out.println("Inner.f()1");
		}
	}
	
	public static void main(String[] args) {
		Pcontents pc = new Pcontents();
		Contents c = pc.new Inner();
		c.f();
	}
}