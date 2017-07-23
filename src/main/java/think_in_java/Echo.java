package think_in_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 标准的输入
 * InputStreamReader 字节流转换字符流
 * @author dell
 *
 */
public class Echo {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			while((s = br.readLine())!= null && s.length() != 0){
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
