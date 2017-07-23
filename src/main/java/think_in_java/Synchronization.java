package think_in_java;

import java.util.*;

/**
 * 同步的collection
 * @author lx
 *
 */
public class Synchronization {
	public static void main(String[] args) {
		//创建同步的容器
		Collection<String> c = Collections.synchronizedCollection(new ArrayList<String>());
		
		List<String> l = Collections.synchronizedList(new ArrayList<String>());
		
		Set<String> s = Collections.synchronizedSet(new HashSet<String>());
		
		Map<String, String> m = Collections.synchronizedMap(new HashMap<String, String>());
	}
}
