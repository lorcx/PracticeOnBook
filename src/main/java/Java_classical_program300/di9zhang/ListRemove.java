package Java_classical_program300.di9zhang;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-21 ����11:17:37
 *������list ���������� ����û��remove
 */
public class ListRemove {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		System.out.println(list.toString());
		list.remove(1);
		System.out.println(list.toString());
		list.remove(2);
		System.out.println(list.toString());
	}
}
