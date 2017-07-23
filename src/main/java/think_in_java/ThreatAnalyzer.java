package think_in_java;

import java.util.Scanner;

/**
 * Threat：威胁
 * Analyzer：分析器
 * Delimiter:定界符
 * @author dell  
 * 模拟扫描一个防火墙日志
 *
 *a li yun
 */
public class ThreatAnalyzer {
	static String threatData = "78.23.123.213@02/10/2005\n"+
								"78.23.123.213@02/10/2005\n"+
								"78.23.123.213@02/10/2005\n"+
								"78.23.123.213@02/10/2005\n"+
								"[next log section with different data format]";
	public static void main(String[] args) {
		Scanner sc = new Scanner(threatData);
		sc.useDelimiter("(\\d+[.]\\d+[.]\\d+[.]\\d+)@\\d{2}/\\d{2}/\\d{4}");
		while(sc.hasNext()){
			System.out.println(sc.next());
		}
	}
}
