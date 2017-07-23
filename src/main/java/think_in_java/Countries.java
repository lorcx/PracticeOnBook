package think_in_java;

import java.util.*;

/**
 * 17章497
 * Countries国家
 * 
 *  自定义的 map set
 *  用内部类替代原有的
 *  用了享元模式
 * @author lx
 *
 */
public class Countries {
	public static final String[][] DATA = { //二位数组测试数据
			{ "1", "a" }, { "1q", "c" },
			{ "2", "a2" }, { "t5", "ce" },
			{ "3", "a3" }, { "t4", "cr" },
			{ "6", "a4" }, { "4r", "cf" }, 
			{ "95", "a5" }, { "r2", "c2" }, 
			{ "345", "a" }, { "r2", "cd" }, 
			{ "04", "a5" }, { "53", "c3" }, 
			
	};

	/**
	 * 内部类
	 * @author lx
	 *
	 */
	private static class FlyWeightMap extends AbstractMap<String, String> {
		/**
		 * 内部类 定制Map.Entry
		 * @author lx
		 *
		 */
		private static class Entry implements Map.Entry<String, String> {
			int index;

			Entry(int index) {
				this.index = index;
			}

			//比较2个的key是否相同
			public boolean equals(Object obj) {
				return DATA[index][0].equals(obj);
			}

			public String getKey() {
				return DATA[index][0];
			}

			public String getValue() {
				return DATA[index][1];
			}

			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
			
			//key的散列
			public int hashCode() {
				return DATA[index][0].hashCode();
			}
		}
		
		/**
		 * 内部类 定制set
		 * @author lx
		 *
		 */
		static class EntrySet extends AbstractSet<Map.Entry<String, String>> {

			private int size;
			
			EntrySet(int size){
			    if(size < 0)
					this.size = 0;
				else if(size > DATA.length)
					this.size = DATA.length;
				else
					this.size = size;
			}
			
			public int size() {
				return size;
			}
			/**
			 * 内部类
			 * @author lx
			 *
			 */
			private class Iter implements Iterator<Map.Entry<String, String>> {
				
				private Entry entry = new Entry(-1);
				
				public boolean hasNext() {
					return entry.index < size - 1;
				}

				public java.util.Map.Entry<String, String> next() {
					entry.index++;
					return entry;
				}

				public void remove() {
					throw new UnsupportedOperationException();
				}
			}

			public Iterator<Map.Entry<String, String>> iterator() {
				return new Iter();
			}
		}

		private Set<Map.Entry<String, String>> entries = new EntrySet(DATA.length);

		public Set<Map.Entry<String, String>> entrySet() {
			return entries;
		}
	}
	
	//没看懂
	static Map<String,String> select (final int size){
		return new FlyWeightMap(){
			@Override //覆盖原来的方法
			public Set<Map.Entry<String, String>> entrySet(){
				return new EntrySet(size);
			}
		};
	}
	
	static Map<String,String> map = new FlyWeightMap();
	
	public static Map<String,String> capitals(){
		return map;
	}
	
	public static Map<String,String> capitals(int size){
		return select(size);
	}
	
	static List<String> names = new ArrayList<String>(map.keySet());
	

	public static List<String> names (){
		return names;
	}

	public static List<String> names (int size){
		return new ArrayList<String>(select(size).keySet());
	}
	
	public static void main(String[] args) {
		System.out.println(capitals(2));
		System.out.println(names(2));
		System.out.println(new HashMap<String,String>(capitals(2)));
		System.out.println(new LinkedHashMap<String,String>(capitals(2)));
		System.out.println(new TreeMap<String,String>(capitals(2)));
		System.out.println(new Hashtable<String,String>(capitals(2)));
		System.out.println(new HashSet<String>(names(2)));
		System.out.println(new LinkedHashSet<String>(names(2)));
		System.out.println(new TreeSet<String>(names(2)));
		System.out.println(new ArrayList<String>(names(2)));
		System.out.println(new LinkedList<String>(names(2)));
		System.out.println(capitals().get("345"));
	 
	}
	
	
	
	
	
}
