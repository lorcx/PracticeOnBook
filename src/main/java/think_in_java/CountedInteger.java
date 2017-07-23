package think_in_java;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类使用  T表示类的引用  ?表示任何的
 * @author dell
 *
 */
public class CountedInteger {
	private static long counter;
	private final long id = counter++; //final只能被赋值一次
	public String toString(){
		return Long.toString(id);
	}
}

/**
 * fill装满的
 * @author dell
 *
 */
class FillList<T> {
	private Class<T> type;
	public FillList (Class<T> type){
		this.type = type;
	}
	
	public List<T> fillList(int num){
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < num; i++) {
			try {
				result.add(type.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return result;
	}
	
	public static void main(String[] args) {
		FillList<CountedInteger> fill = new FillList<CountedInteger>(CountedInteger.class);
		System.out.println(fill.fillList(10));
		
		Class<? super CountedInteger> cn = CountedInteger.class.getSuperclass();
	}
}
