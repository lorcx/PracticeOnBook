package think_in_java;
/**
 * 一个类中有多个string对象，都包含字符串序列，那么这些string对象都映射到同一内存区域，他们的
 * hashcode是相同的	
 * @author lx
 *
 */
public class StringHashCode {

	public static void main(String[] args) {
		String[] hellos = "hello hello".split(" ");
		System.out.println(hellos[0].hashCode());
		System.out.println(hellos[1].hashCode());
	}

}
