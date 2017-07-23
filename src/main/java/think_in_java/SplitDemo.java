package think_in_java;

import java.util.regex.Pattern;

/**
 * split限制切割的数量
 * @author dell
 *
 */
public class SplitDemo {
	public static void main(String[] args) {
		String str = "aa!!bb!!cc!!dd!!ee!!ff";
		String[] s = Pattern.compile("!!").split(str,3);
		for (String string : s) {
			System.out.println(string);
		}
		System.out.println( Pattern.compile("!!").split(str,2).toString());
	}
}
