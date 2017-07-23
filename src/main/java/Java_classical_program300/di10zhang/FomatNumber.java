package Java_classical_program300.di10zhang;

import java.text.DecimalFormat;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-23 ����11:00:24
 *������ ��ʽ������
 */
public class FomatNumber {
	public static void main(String[] args) {
		String patten = "ʱ��#,####;����-##,###Ϊ";
		DecimalFormat df = new DecimalFormat(patten);
		String value = df.format(123.23);
		String v = df.format(-123.45);
		System.out.println(value);
		System.out.println(v);
		
	}
}
