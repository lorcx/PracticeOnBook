package think_in_java;
/**
 * 迭代器设计模式的例子
 * @author dell
 *
 */
public interface Selector {
	boolean end();
	Object current();//current当前的
	void next();
}

/**
 * 序列
 * @author dell
 *
 */
class Sequence{
	private Object[] items;
	private int next = 0;
	//初始化
	public Sequence(int i){
		items = new Object[i];
	}
	public void add(Object o){
		if(next < items.length){
			items[next++] = o;
		}
	}
	private class SequenceSelector implements Selector{
		private int i = 0;
		/**
		 * 判断是不是最后一个
		 */
		public boolean end() {
			return i == items.length;
		}
		
		/**
		 * 返回当前对象
		 */
		public Object current() {
			return items[i];
		}
		/**
		 * 移动到下一个
		 */
		public void next() {
			if(i < items.length){
				i++;
			}
		}
	}
	public Selector getSelector(){
		return new SequenceSelector();
	}
	
	public static void main(String[] args) {
		Sequence seq = new Sequence(10);
		for (int i = 0; i < 10; i++) {
			seq.add(Integer.toString(i));
		}
		Selector se = seq.getSelector();
		while(!se.end()){
			System.out.print(se.current());
			se.next();
		}
	}
	
	
}