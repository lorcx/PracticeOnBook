package think_in_java;

import java.util.Random;

/**
 *  类名.class 不会立即加载类
 * @author dell
 *
 */
public class InitAble {
	static final int  statFinal = 47; //不需要初始化就能使用    //2 如果不是final是3
	static int statFinal1 = ClassInitialingzation.rn.nextInt(1000);//4
    static {
		System.out.println("initialing initable");  //3
	} 
}

class InitAble2 {
	static int statFinal = 147; //6
    static { 
		System.out.println("initialing initable2"); //5
	} 
}

class InitAble3 {
	static int statFinal = 74;//8
    static {
		System.out.println("initialing initable3");//7
	} 
}

class ClassInitialingzation{
	public static Random rn = new Random(47);
	
	public static void main(String[] args) throws Exception {
		Class c = InitAble.class;//延迟加载
		System.out.println("after creating initable ref");  //1
		System.out.println(InitAble.statFinal);
		System.out.println(InitAble.statFinal1);
		System.out.println(InitAble2.statFinal);
		Class c1 = Class.forName("thiniinjava.InitAble3");
		System.out.println("after creating initable3 ref");
		System.out.println(InitAble3.statFinal);
	}
	
}