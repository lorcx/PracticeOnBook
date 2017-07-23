package think_in_java;

import java.util.Iterator;

public class IterableTest implements Iterable<String> {
	public String[] str = "think in java".split(" ");
	
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			int in = 0;
			
			@Override
			public void remove() {
				//foreach中使用会报异常
				throw new UnsupportedOperationException();
				
			}
			
			@Override
			public String next() {
				// TODO Auto-generated method stub
				return str[in++];
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return in < str.length;
			}
		};
	}
	public static void main(String[] args) {
//		List list = Arrays.asList(3,35,6,3,2,5,2);
//		int i = 0;
//		for (Object o : list) {
//			
//			if(o.equals(2)){
//				list.remove(i);
//			}
//			System.out.println(o);
//			i++;
//		}
		
		IterableTest it = new IterableTest();
		for (Iterator<String> it1 =  it.iterator();it1.hasNext();) {
			System.out.println(it1.next());
		}
	}
}
