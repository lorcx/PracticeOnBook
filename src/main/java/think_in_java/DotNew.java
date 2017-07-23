package think_in_java;
/**
 * 创建内部类的方式
 * @author dell
 * 
 *
 */
public class DotNew {
	public class Inner{
		void f(){
			System.out.println("Inner.f()");
		}
	}
	//外部类.new 内部类名创建
	public static void main(String[] args) {
		DotNew dn = new DotNew();
		DotNew.Inner in = dn.new Inner();
		in.f();
	}
}
