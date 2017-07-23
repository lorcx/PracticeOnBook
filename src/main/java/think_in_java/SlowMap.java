package think_in_java;

import java.util.*;

/**
 * 理解hashcode
 * 用arraysList 实现自定义的map
 */
public class SlowMap<k,v> extends AbstractMap<k, v> {

	private List<k> keys = new ArrayList<k>();
	private List<v> values = new ArrayList<v>();
	
	 @Override
	public v put(k key, v value) {
	   v oldVal = get(key);//父类方法
	   if(!keys.contains(key)){
		   keys.add(key);
		   values.add(value);
	   }else{
		   values.set(keys.indexOf(key), value);
	   }
	   return oldVal;
	}
	 
	 @Override
	public v get(Object key) {
		 if(!keys.contains(key)){
			 return null;
		 }
		 return values.get(keys.indexOf(key));
	}
	
	@Override
	public Set<Entry<k, v>> entrySet() {
		Set<Entry<k, v>> set = new HashSet<Entry<k, v>>();
		Iterator<k> ki = keys.iterator();
		Iterator<v> vi = values.iterator();
		while(ki.hasNext()){
			set.add(new MapEntry(ki.next(),vi.next()));
		}
		return set;
	}
	
	public static void main(String[] args) {
		SlowMap<String, String> sm = new SlowMap<String, String>();
		sm.put("k1", "v1");
		sm.put("k2", "v2");
		System.out.println(sm.put("k3", "v3"));
		System.out.println(sm);
		System.out.println(sm.get("k1"));
		
	}
	
}
