package think_in_java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * collection最基本的共同特性
 * @author lx
 *
 */
public class CollectionMethods {

	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.addAll(Countries.names);
		c.add("ten");
		c.add("eleven");
		System.out.println(c);

		Object[] o = c.toArray();
		String[] str = (String[]) c.toArray(new String[0]);
		System.out.println(Collections.max(c));
		System.out.println(Collections.min(c));
		
		Collection c2 = new ArrayList();
		c2.addAll(Countries.names(6));
		c.addAll(c2);
		System.out.println(c);
		c.remove(Countries.DATA[0][0]);
		System.out.println(c);
		c.remove(Countries.DATA[1][0]);
		System.out.println(c);
		c.removeAll(c2);
		System.out.println(c+"==");
		c.addAll(c2);
		String val = Countries.DATA[3][0];
		System.out.println(val);
		System.out.println(c.contains(val));
		System.out.println(c.containsAll(c2));
		
		Collection c3 = ((List)c).subList(3, 5);
		c2.retainAll(c3);
		System.out.println(c2);
		System.out.println(c2.isEmpty());
		c = new ArrayList();
		c.addAll(Countries.names(6));
		System.out.println(c);
		c.clear();
		System.out.println(c);
	}

}
