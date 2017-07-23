package Java_classical_program300.di12zhang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-24 ����9:42:39
 *�������ļ����� �ַ���ͬ�� Ч�ʸ�
 */
public class wenjianfuzhi {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("e:/hello.jpg");
			fos = new FileOutputStream("e:/132.jpg");
			int tmp = 0; 
			while((tmp = fis.read())!= -1){
				fos.write(tmp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
