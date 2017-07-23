package Java_classical_program300.di12zhang;

import java.io.Console;
import java.util.Arrays;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-24 ����10:45:50
 *����������̨��ȡ���� cmd�����ִ��
 */
public class ConsolePassword {
	public static void main(String[] args) {
		Console console = System.console();//��ȡ����̨����
		String str = console.readLine("�������û���");
		char c[] = console.readPassword("����������");
		System.out.println(str);
		for (char d : c) {
			System.out.println(d);
		}
		Arrays.fill(c, 'a');//��ֹ����й¶
		
	}
}
