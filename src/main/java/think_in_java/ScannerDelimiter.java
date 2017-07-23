package think_in_java;

import java.util.Scanner;

/**
 * delimiter:定界符
 * 
 */
public class ScannerDelimiter {
	public static void main(String[] args) {
		Scanner sc = new Scanner("12,23,324,234,123,43");
		sc.useDelimiter("\\s*,\\s*");//使用定界符 \\s匹配任意的空白字符
		while(sc.hasNext()){
			System.out.println(sc.nextInt());
		}
	}
}
