package think_in_java;
/**
 * yolk蛋黄
 * @author dell
 *
 */
public class Eggs {
	private Yolk y = new Yolk();
	/**
	 * 子类继承父类时，会调用父类的构造方法。如果父类只有一个有参的构造方法，那么子类就必须也有一个有参数的构造方法。或者父类添加个午餐的构造方法、
	 * @param yy
	 */
//	public Eggs(){
//		
//	}
	public Eggs(){//Yolk yy
		
		System.out.println("eggs.eggs()");
	}
	public class Yolk{
		public Yolk(){
			System.out.println("eggs.yolk()");
		}
		public void f(){
			System.out.println("eggs.yolk.f()");
		}
		
	}
	public void insertY(Yolk yy){
		this.y = yy;
	}
	public void g(){
		y.f();
	}
}

class Eggs2 extends Eggs{
	 public Eggs2() {
		insertY(new Yolk());
		System.out.println("eggs2.eggs2()");
	}

	public class Yolk extends Eggs.Yolk{
		public Yolk(){
			System.out.println("eggs2.yolk()");
		}
		public void f(){
			System.out.println("eggs2.yolk.f()");
		}
	}
	
	public static void main(String[] args) {
		Eggs eg = new Eggs2();
		eg.g();
	}
}