package think_in_java;

import java.io.*;

/**
 * 重定向输出
 * @author dell
 *
 */
public class Redirecting {
	public static void main(String[] args) {
		PrintStream console = System.out;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("F:\\3.txt"));//读取文件
			PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("F:\\1.txt")));//输入目录
			System.setIn(in);
			System.setOut(out);
			System.setErr(out);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = "";
			while((s = br.readLine()) != null){
				System.out.println(s);
			}
			out.close();
			in.close();
			System.setOut(console);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
