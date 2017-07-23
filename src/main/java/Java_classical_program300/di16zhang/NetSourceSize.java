package Java_classical_program300.di16zhang;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2015-1-2 ����9:15:22
 *��������ȡ������Դ��С
 */
public class NetSourceSize {
	public static void main(String[] args) {
		String str ="http://www.baidu.com";
		try {
			URL url = new URL(str);
			URLConnection uc = url.openConnection();
			uc.connect();//������
			System.out.println(uc.getContentLength());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
