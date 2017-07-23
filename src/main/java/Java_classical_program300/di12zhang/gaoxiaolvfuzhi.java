package Java_classical_program300.di12zhang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-24 ����9:59:45
 *��������Ч�ʸ���
 */
public class gaoxiaolvfuzhi {
	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader("e:/password.txt");//Ҫ�����ļ�
			fw = new FileWriter("e:/123.txt");//Ҫд����λ��
			br = new BufferedReader(fr);
			pw = new PrintWriter(fw);
			String flag = "";
			while((flag = br.readLine())!= null){//��һ��
				pw.println(flag);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw != null){
				pw.close();
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fw != null){
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
