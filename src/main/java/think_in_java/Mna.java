package think_in_java;
/**
 * 多层嵌套类
 * @author dell
 *
 */
public class Mna {
	private void f(){}
	class a{
		 void d(){}
		 class e{
				void h(){
					System.out.println("h1");
				}
		 }
	}
}

class MnaTest{
	public static void main(String[] args) {
		Mna mna = new Mna();
		Mna.a a = mna.new a();
		Mna.a.e mnab = a.new e();
		mnab.h();
	}
}