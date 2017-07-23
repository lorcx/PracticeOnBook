package think_in_java;
/**
 * 嵌套类 static 的内部类
 * @author dell
 *
 */
public class TestBed {
	public void f(){
		System.out.println("f");
	}
	public static class Test{
		public static void main(String[] args) {
			TestBed t = new TestBed();
			t.f();
		}
	}
}
