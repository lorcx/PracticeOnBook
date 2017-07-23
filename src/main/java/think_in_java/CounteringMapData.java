package think_in_java;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 享元
 * @author lx
 *
 */
public class CounteringMapData extends AbstractMap<Integer, String> {
	
	private int size;
	
	private static String[] chars = {"a","b","c","d"};
	
	public CounteringMapData(int size) {
		this.size = size < 0 ? 0 : size;
	}
	
	private static class Entry implements Map.Entry<Integer, String>{
		int index;
		
		public Entry(int index) {
			this.index = index;
		}
		
		@Override
		public boolean equals(Object o) {
			return Integer.valueOf(index).equals(o);
		}
		
		@Override
		public Integer getKey() {
			return index;
		}

		@Override
		public String getValue() {
			return chars[index % chars.length] + 
					Integer.toString(index / chars.length);
		}

		@Override
		public String setValue(String value) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int hashCode() {
			return Integer.valueOf(index).hashCode();
		}
	}
	
	@Override
	public Set<Map.Entry<Integer, String>> entrySet() {
		Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<Map.Entry<Integer,String>>();
		for (int i = 0; i < size; i++) {
			entries.add(new Entry(i));
		}
		return entries;
	}

	public static void main(String[] args) {
		System.out.println(new CounteringMapData(20));
	}
}
