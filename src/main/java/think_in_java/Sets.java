package think_in_java;

import java.util.HashSet;
import java.util.Set;

/**
 * 泛型set
 * @author dell
 *
 */
public class Sets {
	/**
	 * 并集
	 */
	public static <T> Set<T> union(Set<T> a, Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}
	/**
	 *intersection 交集
	 */
	public static <T> Set<T> intersection (Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);//两个set的交集
		return result;
	}
	/**
	 *difference:差异
	 */
	public static <T> Set<T> difference (Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<T>(a);
		result.removeAll(b); 
		return result;
	}
	
	/**
	 * complement:补足
	 */
	public static <T> Set<T> complement (Set<T> a, Set<T> b) {
		return difference(union(a, b), intersection(a, b));
	}
	
}
