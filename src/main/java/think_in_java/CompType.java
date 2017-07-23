package think_in_java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 数组的比较
 * 
 * @author lx
 *
 */

public class CompType implements Comparable<CompType> {
	int i;
	int j;
	public static int count = 1;
	
	public String toString() {
		String result = "[ i=" + i + ",j=" + j + "]";
		if(i % 3 ==0){
			result = "\n";
		}
		return result;
	}
	
	/**
	 * 比较大小
	 */
	public int compareTo(CompType o) {
		return i < o.i ? -1 : (i == o.i ? 0 : 1);
	}
	
	public static void main(String[] args) {
		//书上用的是随机生成数据到数组，所以省略了。。。
		//如果不实现Comparable接口，就不能用sort方法
//		CompType [] ct = new CompType [10];
//		Random rn = new Random(100);
//		Arrays.fill(ct, rn.nextInt(47));
//		System.out.println(ct);
//		
//		Arrays.sort(ct);
//		
//		System.out.println(ct);
		
	}

}

/**
 * 自定义的compator
 */
class CompTypeCompator implements Comparator<CompType> {

	public int compare(CompType o1, CompType o2) {
		return o1.j < o2.j ? -1 : (o1.j == o2.j ? 0 :1);
	}
	
}

class CompTypeCompatorTest {
	public static void main(String[] args) {
		Arrays.sort(new CompType[5], new CompTypeCompator());//使用自定义的类进行比较
	}
}





