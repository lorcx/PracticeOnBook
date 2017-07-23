package think_in_java;

import java.util.WeakHashMap;

/**
 * weakHashMap
 * @author lx
 *
 */
public class Element {

	private String ident;
	
	public Element(String ident) {
		this.ident = ident;
	}

	@Override
	public String toString() {
		return ident;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Element && ((Element)obj).ident.equals(ident);
	}
	
	@Override
	public int hashCode() {
		return ident.hashCode();
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalizeing:" + getClass().getSimpleName() + ":" + ident);
	}
}

class key extends Element{
	public key(String ident) {
		super(ident);
	}
}

class value extends Element{
	public value(String ident) {
		super(ident);
	}
}

class CanonicalMapping{
	public static void main(String[] args) {
		int size = 1000;
		if(args.length > 0)
			size = new Integer(args[0]);
		key[] keys = new key[size];
		WeakHashMap<key, value> whm = new WeakHashMap<key, value>();
		for (int i = 0; i < size; i++) {
			key k = new key(Integer.toString(i));
			value v = new value(Integer.toString(i));
			if(i % 3 == 0)
				keys[i] = k;//将这个指向普通对象的引用，放到了数组中，垃圾回收器就不能回收这个对象。
			whm.put(k, v);
		}
		System.gc();
	}
}
