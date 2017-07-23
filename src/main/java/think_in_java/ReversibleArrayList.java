package think_in_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 可逆的迭代器
 * 设计模式：适配器模式
 * Reversible：可逆的
 * @author dell
 *
 */
public class ReversibleArrayList<T> extends ArrayList<T> {
	public ReversibleArrayList(Collection<T> c) {
		super(c);
	}
	
	public Iterable<T> rever(){
		 return new Iterable<T>() {
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private int current = size() - 1;//当前位置
					public void remove() {
						throw new UnsupportedOperationException();
					}
					
					public T next() {
						return get(current--);
					}
					
					public boolean hasNext() {
						return current > -1;
					}
				};
			}
		};
	}
}

/**
 * 适配器方法
 * 
 */
class AdapterMethod{
	public static void main(String[] args) {
		ReversibleArrayList<Integer> ra = new ReversibleArrayList<Integer>(Arrays.asList(3, 5, 6, 123, 2, 3, 5, 3));
		for (Integer in : ra) {
			System.out.println("正序"+in);
		}
		
		for (Integer in1 : ra.rever()) {
			System.out.println("反序"+in1);
		}
	}
}
