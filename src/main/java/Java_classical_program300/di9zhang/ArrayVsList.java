package Java_classical_program300.di9zhang;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-21 ����10:19:43
 *������ ����ͼ���Ч�ʱȽ�
 */
public class ArrayVsList {
	public static void main(String[] args) {
		int counter = 0;//������
		for (int i = 0; i < 1000; i++) {
			if(ArrayVsList.isArrayEifficient()){
				counter++;
			}
		}
		System.out.println(counter);
	}
	@SuppressWarnings("all")
	public static boolean isArrayEifficient(){//Eifficient��Ч�ʵ�
		char[] c = new char[1000];
		List list = new ArrayList(c.length);
		//��ֵ
		for (int i = 0; i < c.length; i++) {
			c[i] = (char) (65 + i % 26);
			list.add((char)(65 + i % 26));
		}
		//����
		long currentTime = System.nanoTime();//��ȡ��ǰϵͳ����ʱ��
		new String(c);
		System.gc();//�����ڴ�
		long arrayTime = System.nanoTime() - currentTime;
		//list
	     currentTime = System.nanoTime();//��ȡ��ǰϵͳ����ʱ��
	     list.toString();
		long listTime = System.nanoTime() - currentTime;
		return arrayTime < listTime;
	}
}
