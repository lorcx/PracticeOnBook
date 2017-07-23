package think_in_java;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 潜在的类型机制2：应用于序列
 * rotate:旋转
 * @author lx
 *
 */
public class Apply {
	public static <T,S extends Iterable<? extends T>> void apply(S seq,Method f,Object...args){
		for(T t : seq){
			try {
				f.invoke(t, args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


class Shape1{
	public void rotate(){
		System.out.println(this+"shape1");
	}
	
	public void resize(int newSize){
		System.out.println(this+"resize"+newSize);
	}
}

class Square1 extends Shape1{}

class FillList1<T> extends ArrayList<T> {
	public FillList1(Class<? extends T> type,int size){
		for (int i = 0; i < size; i++) {
			try {
				add(type.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
}

class SimpleQueue<T> implements Iterable<T> {
	
	private LinkedList<T> ll = new LinkedList<T>();
	
	public void add(T t){
		ll.offer(t);
	}
	
	public T get(){
		return ll.poll();
	}
	
	public Iterator<T> iterator() {
		return ll.iterator();
	}
}

class ApplyTest{
	public static void main(String[] args) {
		List<Shape1> shapes = new ArrayList<Shape1>();
		for (int i = 0; i <10; i++) {
			shapes.add(new Shape1());
		}
		
		try {
			Apply.apply(shapes, Shape1.class.getMethod("rotate"));
			Apply.apply(shapes, Shape1.class.getMethod("resize"),5);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		List<Square1> Squares = new ArrayList<Square1>();
		for (int i = 0; i < 10; i++) {
			shapes.add(new Square1());
		}
		
		try {
			Apply.apply(Squares, Square1.class.getMethod("rotate"));
			Apply.apply(Squares, Square1.class.getMethod("resize"),5);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		try {
			Apply.apply(new FillList1<Shape1>(Shape1.class,10),Shape1.class.getMethod("rotate"));
			Apply.apply(new FillList1<Shape1>(Shape1.class,10),Shape1.class.getMethod("resize"),5);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		SimpleQueue<Shape1> sq = new SimpleQueue<Shape1>();
		for (int i = 0; i < 5; i++) {
			sq.add(new Shape1());
			sq.add(new Square1());
		}
		
		try {
			Apply.apply(sq,Shape1.class.getMethod("rotate"));
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
	}
}




























