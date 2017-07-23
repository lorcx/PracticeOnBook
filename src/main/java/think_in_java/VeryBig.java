package think_in_java;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * 持有引用 17.12
 * @author lx
 *
 */
public class VeryBig {
	public static final int SIZE = 10000;
	private String str;
	
	public VeryBig(String str) {
		this.str = str;
	}
	
	@Override
	public String toString() {
		return str;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize:"+str);
	}

}

class References{
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();
	
	public static void checkQueue(){
		Reference<? extends VeryBig> rf = rq.poll();
		if(rf != null)
			System.out.println("in queue:"+rf.get());
	}
		
	public static void main(String[] args) {
		int size = 10;
		if(args.length > 0)
			size = new Integer(args[0]);
		
		LinkedList<SoftReference<VeryBig>> ll = new LinkedList<SoftReference<VeryBig>>();
		for (int i = 0; i < ll.size(); i++) {
			ll.add(new SoftReference<VeryBig>(new VeryBig("soft" + i)));
			System.out.println(ll.getLast());
			checkQueue();
		}
		
		LinkedList<WeakReference<VeryBig>> wr = new LinkedList<WeakReference<VeryBig>>();
		for (int i = 0; i < wr.size(); i++) {
			wr.add(new WeakReference<VeryBig>(new VeryBig("weak" + i)));
			System.out.println(wr.getLast());
			checkQueue();
		}
		
		SoftReference<VeryBig> srf = new SoftReference<VeryBig>(new VeryBig("soft"));
		WeakReference<VeryBig> wrf = new WeakReference<VeryBig>(new VeryBig("soft"));
		
		System.gc();
		
		LinkedList<PhantomReference<VeryBig>> pr = new LinkedList<PhantomReference<VeryBig>>();
		for (int i = 0; i < wr.size(); i++) {
			pr.add(new PhantomReference<VeryBig>(new VeryBig("phantom"), rq));
			System.out.println(wr.getLast());
			checkQueue();
		}
		
	}
}