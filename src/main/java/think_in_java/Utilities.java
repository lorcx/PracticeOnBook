package think_in_java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 容器中的实用方法
 * @author dell
 *
 */
public class Utilities {
	static List<String> list = Arrays.asList("one two three four five six one".split(" "));
	public static void main(String[] args) {
		//disjoint 两个集合没用相同的元素 返回true
		//singletonList产生只包含特定元素的特定 如 list{four}
		System.out.println(Collections.disjoint(list, Collections.singletonList("four")));
		System.out.println(Collections.max(list));
		System.out.println(Collections.min(list));
		System.out.println(Collections.max(list, String.CASE_INSENSITIVE_ORDER));
		System.out.println(Collections.min(list, String.CASE_INSENSITIVE_ORDER));
		List<String> subList = Arrays.asList("four five six".split(" "));
		System.out.println(Collections.indexOfSubList(list, subList));
		System.out.println(Collections.lastIndexOfSubList(list, subList));
		Collections.reverse(list);
		System.out.println(list);
		//将3这个位置之前的元素移到队尾
		Collections.rotate(list, 3);
		System.out.println(list);
		Collections.swap(list, 0, list.size() - 1);
		//返回该元素重复的个数
		System.out.println(Collections.frequency(list, "one"));
		//返回一个大小为3的list ,且里边被填充 ，其中所有的引用指向第二个参数
		System.out.println(Collections.nCopies(3, "snap"));
		Collections.fill(list, "hh");
		System.out.println(list);
		
	}
}
