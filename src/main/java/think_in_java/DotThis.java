package think_in_java;
/**
 * .this的使用（返回外部类的引用）
 * @author dell
 *
 */
public class DotThis {
	void f(){
		System.out.println("DotThis.f()");
	}
	public class Inner{
		public DotThis getOut(){
			return DotThis.this;//返回外部类的引用
		}
	}
	/**
	 * 获取内部类的实例
	 * @return
	 */
	public Inner getInner(){
		return new Inner();
	}
	public static void main(String[] args) {
		DotThis dt = new DotThis();
		dt.getInner().getOut().f();
	}
	
}
