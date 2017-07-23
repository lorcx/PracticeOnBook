package think_in_java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则  
 * group返回以匹配的部分
 * @author dell
 *
 */
public class Groups {
	static final String str = "Chinadaily.com.cn is " +
							  "the largest English portal" +
							  " in China, providing news, " +
							  "business information, BBS," +
							  " learning materials. " +
							  "The Website has channels as China";
	public static void main(String[] args) {
		Matcher m = Pattern.compile("(?m)(\\s+)()\\s((\\s+)\\s+(\\s)+)$").matcher(str);  //\\s非空格字符
		while(m.find()){
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.println(m.group(i));
			}
		}
	}
}
