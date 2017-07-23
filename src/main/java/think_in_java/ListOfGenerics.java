package think_in_java;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型数组
 * @author dell
 *
 * @param <T>
 */
public class ListOfGenerics<T> {
	private List<T> list = new ArrayList<T>();
	
	public void add(T e){
		list.add(e);
	}
	
	public T get(int index){
		return list.get(index);
	}
}



