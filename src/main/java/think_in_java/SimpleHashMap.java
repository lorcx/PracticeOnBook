package think_in_java;

import java.util.*;

/**
 * 自定义的hashmap
 * 
 * @author dell
 *
 */
public class SimpleHashMap<k,v> extends AbstractMap<k, v> {

	static final int SIZE = 997;
	
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<k, v>>[] buckets = new LinkedList[SIZE];
	
	@Override
	public v put(k key, v value) {
		v oldValue = null;
		int index = Math.abs(buckets.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<MapEntry<k, v>>();
		LinkedList<MapEntry<k, v>> bucket = buckets[index];
		MapEntry<k,v> pair = new MapEntry<k,v>(key,value);
		boolean found = false;
		ListIterator<MapEntry<k, v>> it = bucket.listIterator();
		while(it.hasNext()){
			MapEntry<k, v> iPair = it.next();
			if(iPair.getKey().equals(key)){
				oldValue = iPair.getValue();
				found = true;
				it.set(pair);
				break;
			}
		}
		if(!found)
			buckets[index].add(pair);
		return oldValue;
	}
	
	@Override
	public v get(Object key) {
		int index = Math.abs(buckets.hashCode()) % SIZE;
		if(buckets[index] == null)
			return null;
		for (Map.Entry<k, v> entry : buckets[index]) {
			if(entry.getKey().equals(key)){
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public Set<Entry<k, v>> entrySet() {
		Set<Entry<k, v>> set = new HashSet<Entry<k,v>>();
		for(LinkedList<MapEntry<k, v>> bucket : buckets){
			if(bucket == null) 
				continue;
			for (MapEntry<k, v> entry : bucket) {
				set.add(entry);	
			}
		}
		return set;
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String, String> sm = new SimpleHashMap<String, String>();
		sm.put("k1", "v1");
		sm.put("k2", "v2");
		System.out.println(sm.put("k3", "v3"));
		System.out.println(sm);
		System.out.println(sm.get("k1"));
	}

}
