package think_in_java;

import java.util.ArrayList;
import java.util.List;

/**
 * 无意识递归
 * Infinite:无限的
 * Recursion:递归
 * @author dell
 *
 */
public class InfiniteRecursion {
	
	public String toString(){
		return super.toString();
		//return "memory="+this;//这里会类型转换，this转string会调用toString()，也就是本方法，最后就会变成死循环，导致内存溢出
	}
	public static void main(String[] args) {
		List<InfiniteRecursion> list = new ArrayList<InfiniteRecursion>();
		for (int i = 0; i < 10; i++) {
			list.add(new InfiniteRecursion());
		}
		System.out.println(list.toString());
	}
}
