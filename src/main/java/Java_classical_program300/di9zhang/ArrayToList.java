package Java_classical_program300.di9zhang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-21 ����10:58:16
 *�����������list֮���ת��
 */
public class ArrayToList {
	public static void main(String[] args) {
		int[] a = new int[5];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		List list = Arrays.asList(a);//����ת����list
		//listת����
		List list1 = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			list1.add(i);
		}
		Integer[] in = (Integer[]) list1.toArray(new Integer[]{}); 
	}
}
