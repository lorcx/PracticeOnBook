package think_in_java;

import java.util.AbstractList;

/**
 * 17章容器
 * 定制list
 * 享元
 * 
 */
public class CounteringIntegerList extends AbstractList<Integer> {
	private int size;
	
	public CounteringIntegerList(int size) {
		this.size = size < 0 ? 0 : size;//这句执行完毕后就会创建长度为30的容器
	}

	@Override
	public Integer get(int index) {
		return Integer.valueOf(index);
	}

	@Override
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		System.out.println(new CounteringIntegerList(30));
		System.out.println(new CounteringIntegerList(30).get(0));
//		List<Integer> in = new ArrayList<Integer>(30);
//		System.out.println(in);
	}
	
}
