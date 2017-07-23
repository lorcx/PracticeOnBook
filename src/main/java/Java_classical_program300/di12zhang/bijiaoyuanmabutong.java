package Java_classical_program300.di12zhang;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-25 ����11:07:44
 *�������Ƚ�2���ļ��Ĳ�ͬ
 */
public class bijiaoyuanmabutong {
	public static void main(String[] args) {
		FileReader fr1 = null;
		FileReader fr2 = null;
		FileWriter fw = null;
		LineNumberReader lnr1 = null;
		LineNumberReader lnr2 = null;
		try {
			fr1 = new FileReader("e:/123.txt");
			fr2 = new FileReader("e:/234.txt");
			lnr1 = new LineNumberReader(fr1);
			lnr1 = new LineNumberReader(fr2);
			String str1 = lnr1.readLine();
			String str2 = lnr2.readLine();
			while((lnr1 != null)&&(lnr2 != null)){
				if(!(str1.equals(str2))){
					System.out.println("��"+lnr1.getLineNumber()+"�в����");
				}else{
					System.out.println(str1);
					System.out.println(str2);
				}
				str1 = lnr1.readLine();
				str2 = lnr2.readLine();
				
			}
			//��һ�бȵڶ��ж�
			if(str1 == null && str2 != null){
				System.out.println("��"+lnr1.getLineNumber()+"���ѽ���");
			}
			if(str1 != null && str2 == null){
				System.out.println("��"+lnr2.getLineNumber()+"���ѽ���");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
