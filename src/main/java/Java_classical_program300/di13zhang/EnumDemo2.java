package Java_classical_program300.di13zhang;

import di13zhang.EnumDemo.demo;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-28 ����9:35:11
 *������ ö���ඨ�� ���󷽷�
 *
 * �� static final 
 */
public class EnumDemo2 {
	public enum Demo{
		NORTH{public String getName(){return "��";}},
		EAST{public String getName(){return "��";}},
		SOUTH{public String getName(){return "��";}},
		WEST{public String getName(){return "��";}};
		public abstract String getName();//���������±�
	}
	public static void main(String[] args) {
		Demo east = Demo.EAST;
		int i = Demo.NORTH.ordinal();
		System.out.println(i);
		System.out.println(east.getName());
		
		for (demo d : demo.values()) {
			System.out.println(d);
		}
		//ö��Ĭ��ʵ����comparable�ӿ�
		System.out.println(Demo.EAST.compareTo(Demo.SOUTH));
	}
}
