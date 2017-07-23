package think_in_java;

import java.io.PrintWriter;

/**
 * 将system.out转成printWriter
 * @author dell
 *
 */
public class ChangeSystemOut {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out,true);
		pw.println("hellow world!");
	}
}
