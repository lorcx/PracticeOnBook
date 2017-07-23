package think_in_java;
/**
 * definition 定义的
 * semicolon 分号
 * required 需要的
 * case 情况
 * 匿名内部类
 * @author dell
 * @param <Con>
 *
 */
public class Parcel7  {
//	public Con getContents(){
//		return new Con(){
//			private int i = 11;
//			public int getVal(){
//				return i;
//			}
//		};
//	}
	/**
	 * 内部匿名类的简化形式, 下面相当于上面的形式
	 * @author dell
	 *
	 */
	 class MyContents implements Contents{
		public void f() {
			System.out.println("f");
		}
	 }
	 
	public Contents getContents(){
		return new MyContents();
	}
	
	public static void main(String[] args) {
		Parcel7 p = new Parcel7();
		Contents c = p.getContents();
		c.f();
	}
}
