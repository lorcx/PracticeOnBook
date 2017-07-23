package think_in_java;

import java.util.Iterator;

class IteratorDemo{
	 int[] in = new int[10];
}


class IteratorTest extends IteratorDemo{
	public Iterator<Integer> iterator(){
		
		/**
		 * 匿名内部类实现接口
		 */
		return new Iterator<Integer>() {
			private int i = 0;
			
			public void remove() {
				// 可选
				//foreach中使用会报异常
				
			}
			
			@Override
			public Integer next() {
				 
				return in[i++];
			}
			
			@Override
			public boolean hasNext() {
				 
				return i < in.length;
			}
		};
	}
}
