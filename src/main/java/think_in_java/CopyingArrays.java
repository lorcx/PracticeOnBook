package think_in_java;

import java.util.Arrays;

/**
 * 拷贝数组
 * @author lx
 *
 */
public class CopyingArrays {
	public static void main(String[] args) {
		/**
		 * System.arraycopy
		 * i, 0, j, 0, i.length
		 * i要拷贝的数组
		 * 0从i数组的第0个拷贝
		 * j拷贝到的目标数组
		 * i.length拷贝的数组结束位置
		 * 
		 */
		int i[] = new int[7];
		int j[] = new int[10];
		Arrays.fill(i, 47);
		Arrays.fill(j, 99);
		System.out.println(Arrays.toString(i));
		System.out.println(Arrays.toString(j));
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println(Arrays.toString(i));
		int k[] = new int[5];
		Arrays.fill(k, 103);
		System.arraycopy(i, 0, k, 0, k.length);
		System.out.println(Arrays.toString(k));
		System.arraycopy(k, 0, i, 0, k.length);
		System.out.println(Arrays.toString(i));
		int u[] = new int[10];
		int v[] = new int[5];
		Arrays.fill(u, new Integer(47));
		Arrays.fill(v, new Integer(99));
		System.out.println(Arrays.toString(u));
		System.out.println(Arrays.toString(v));
		System.arraycopy(v, 0, u, u.length / 2, v.length);
		System.out.println(Arrays.toString(u));
		
		
	}
}
