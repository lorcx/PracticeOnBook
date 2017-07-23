package Java_classical_program300.di9zhang;

import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-22 ����10:05:06
 *������TreeSet����  NavigableSet
 */
public class TreeSetDemo {
	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		Collections.addAll(set, 1,2,3,4,5,6);
		Iterator it = set.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
		System.out.println();
		Iterator it1 = set.descendingIterator();
		while (it1.hasNext()) {
			System.out.print(it1.next()+" ");
		}
		System.out.println();
		//����3����СԪ��
		int heier = set.higher(3);
		System.out.println(heier);
		//С��3�����Ԫ��
		int lower = set.lower(3);
		System.out.println(lower);
		// <=3
		NavigableSet<Integer> nSet = set.headSet(3, true);
		// >=2 <=4
		NavigableSet<Integer> sSet = set.subSet(2, true,4,true);
		// >=3
		NavigableSet<Integer> tSet = set.tailSet(3,true);
		System.out.println(nSet);
		System.out.println(sSet);
		System.out.println(tSet);
		
		int pof = set.pollFirst();
		int pol = set.pollLast();
		//���򼯺�����С��
		System.out.println(pof);
		//���򼯺�������
		System.out.println(pol);
		
		
	}
}
