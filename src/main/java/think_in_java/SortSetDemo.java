package think_in_java;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * sortSet
 * 
 * treeSet
 * 
 * @author lx
 * 
 */
public class SortSetDemo {

	public static void main(String[] args) {
		SortedSet set = new TreeSet();
		Collections.addAll(set, "a b c d e f g".split(" "));
		System.out.println(set);
		String low = (String) set.first();
		String hight = (String) set.last();
		
		System.out.println(low);
		System.out.println(hight);

		Iterator it = set.iterator();
		for (int i = 0; i < 6; i++) {
			if(i == 3)
				low = (String) it.next();
			else if (i == 6)
				hight = (String) it.next();
			else
				it.next();
		}
		System.out.println(low);
		System.out.println(hight);
		System.out.println(set.subSet(low, hight));
		System.out.println(set.headSet(hight));//小于这个节点之前的集合
		System.out.println(set.tailSet(low));//大于或等于这个节点之前的集合
		
		
	}
}
