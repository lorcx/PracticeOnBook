package think_in_java;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 17章set使用
 * @author lx
 *
 */
public class SetType {
	int i;
	
	public SetType(int i) {
		this.i = i;
	}
	
	/**
	 * 为了保证元素的一致性
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof SetType && ((SetType)obj).i == i;
	}
	
	@Override
	public String toString() {
		return Integer.toString(i);
	}
}

/**
 * hashset必须有hashcode
 * @author lx
 *
 */
class HashType extends SetType{

	public HashType(int i) {
		super(i);
	}

	@Override
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType> {

	public TreeType(int i) {
		super(i);
	}

	@Override
	public int compareTo(TreeType o) {
		return o.i < i ? -1 : (o.i == i ? 0 : 1); //常见的编程错误 i-i2 他们只有是无符号位是才能正常工作
	}
}

class TypeForSets {
	
	static<T> Set<T> fill(Set<T> set, Class<T> c){
		for (int i = 0; i < 10; i++) {
			try {
				set.add(c.getConstructor(int.class).newInstance(i));
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
		return set;
	}
	
	static <T> void test(Set<T> set, Class<T> c){
		fill(set,c);//测试重复添加
		fill(set,c);
		fill(set,c);
		System.out.println(set);
	}
	
	public static void main(String[] args) {
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(), HashType.class);
		test(new TreeSet<TreeType>(), TreeType.class);
		//没有实现 hashcode 排序 。。。等，set就变成了可以重复的了
		test(new HashSet<SetType>(), SetType.class);//没有hashcode
		test(new HashSet<TreeType>(), TreeType.class);//没有hashcode
		test(new LinkedHashSet<SetType>(), SetType.class);//没有hashcode
		test(new LinkedHashSet<TreeType>(), TreeType.class);//没有hashcode
		
		
//		try {
//			test(new TreeSet<SetType>(), SetType.class);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			test(new TreeSet<HashType>(), HashType.class);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
}












