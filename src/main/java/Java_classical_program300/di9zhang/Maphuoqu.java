package Java_classical_program300.di9zhang;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-22 ����10:38:45
 *��������ȡmap���еļ�ֵ��
 */
public class Maphuoqu {
	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		//��ֵ
		for (int i = 0; i < 10; i++) {
			map.put(i, i);
		}
		//����1
		Set<Entry<Integer, Integer>> set = 	map.entrySet();
		for (Entry<Integer, Integer> entry : set) {
			System.out.println("key"+entry.getKey()+"||"+"value"+entry.getValue());
		}
		//����2
		Iterator<Entry<Integer, Integer>> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
