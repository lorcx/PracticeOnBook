package Java_classical_program300.di12zhang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-24 ����10:18:10
 *���������ļ��е����ּ���
 */
public class duiwenjianzhongdeshuzijisuan {
	public static void main(String[] args) {
		Scanner input = null;
		int i = 0;
		try{
			input = new Scanner(new File("e:/123.txt"));
			while(input.hasNext()){//�Ƿ���ڿɶ�ȡ������
				if(input.hasNextInt()){//�Ƿ���ڿɶ�ȡ������
					i += input.nextInt();
				}else{
					input.next();
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(input != null){
				input.close();
			}
		}
		System.out.println(i);
	}
}
