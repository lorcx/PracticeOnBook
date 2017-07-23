package think_in_java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用适配器仿真潜在类型机制
 * @author lx
 *
 */
public class Fill2 {
	public static <T> void fill(AddAble<T> ab,Class<? extends T> classToken,int size){
		for (int i = 0; i < size; i++) {
			try {
				ab.add(classToken.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static <T> void fill(AddAble<T> ab,Generator<T> g,int size){
		for (int i = 0; i < size; i++) {
			try {
				ab.add(g.next());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
}

interface AddAble<T> {void add(T t);}


class AddAbleCollectionAdapter<T> implements AddAble<T> {
	
	private Collection<T> c;
	
	public AddAbleCollectionAdapter(Collection<T> c) {
		this.c = c;
	}
	
	public void add(T item) {
		c.add(item);
	}
}

class Adapter {
	public static <T> AddAble<T> collectionAdapter(Collection<T> c){
		return new AddAbleCollectionAdapter<T>(c);
	}
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements AddAble<T>{
	public void add(T t) {
		super.add(t);
	}
}

class FillTest2 {
	public static void main(String[] args) {
		List<Coffice> list = new ArrayList<Coffice>();
		Fill2.fill(new AddAbleCollectionAdapter<Coffice>(list), Coffice.class, 3);
		Fill2.fill(Adapter.collectionAdapter(list), Coffice.class, 3);
		
		for (Coffice c : list) {
			System.out.println(c);
		}
		
		AddableSimpleQueue<Coffice> asq = new AddableSimpleQueue<Coffice>();
		Fill2.fill(asq, Coffice.class, 3);
		Fill2.fill(asq, Coffice.class, 3);
		
		for (Coffice c : asq) {
			System.out.println(c);
		}
		
	}
}















