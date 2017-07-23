package think_in_java;
/**
 *  insect 昆虫
 * 	static 初始化 这个类解释了 执行顺序
 *   1.由于是继承，首先去执行父类的static的数据域，然后执行子类的static的数据域
 *   2.由于是new子类，所以先去执行父类的非static数据域和他的构造方法，
 *     然后执行子类的非static数据域和构造方法
 *   
 *  
 * @author dell
 *
 */
@SuppressWarnings("all")
public class Insect {
	private int i = 9;
	protected int j;
	private static int x1 = printInit("insect.x1 init");
	public Insect() {
		System.out.println("i="+i+"j="+j);
	}
	 static int printInit(String str) {
		System.out.println(str);
		return 47;
	}
}

/**
 * beetle 甲壳虫
 * @author dell
 *
 */
@SuppressWarnings("all")
class Beetle extends Insect{
	private int k = printInit("beetle.k init");
	private static int x2 = printInit("beetle.x2 init"); 
	public Beetle() {
		System.out.println("k="+k);
		System.out.println("j="+j);
	}
	public static void main(String[] args) {
		System.out.println("beetle constructor");
		Beetle beetle = new Beetle();
	}
}
