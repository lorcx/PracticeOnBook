package think_in_java;

import java.util.*;


/**
 * 为获取支持的操作
 * 
 * @author lx
 * 
 */
public class Unsupported {

	static void test(String msg, List list) {
		System.out.println(msg);
		Collection c = list;     //任何对底层数据结构的尺寸修改都会抛异常,应该使用 addAll或当作构造方法传入
		Collection c1 = list.subList(0, 2);
		Collection c2 = new ArrayList(c1);

		try {
			c.retainAll(c2);
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			c.clear();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			c.add(c2);
		} catch (Exception e) {
			System.out.println(e);
		}
	
		try {
			c.addAll(c2);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			c.remove(c2);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			list.set(0,"x");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		List list = Arrays.asList("a b c d e f g h i".split(" "));
		test("1",list);
		test("2",new ArrayList(list));
		test("3", Collections.unmodifiableList(new ArrayList(list)));
	}
}
