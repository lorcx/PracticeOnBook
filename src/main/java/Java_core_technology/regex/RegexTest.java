package Java_core_technology.regex;

import java.lang.String;import java.lang.System;import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author dell
 *
 */
public class RegexTest {
	public static void main(String[] args) {
		String str = "11:59am";
		String regex = "((1?[0-9]):([0-5][0-9]))[ap]m";//正则
		Pattern pattern = Pattern.compile(regex);//正则
		Matcher matcher = pattern.matcher(str);//要验证的字符串
		if(matcher.matches()){//如果匹配
			int count = matcher.groupCount();//群组的长度
			for (int i = 0; i < str.length(); i++) {
				for (int j = 1; j < count; j++) {
					if(i == matcher.start(j) && i == matcher.end(j)){
						System.out.println("()");
					}
				}
				for (int j = 1; j <= count; j++) {
					if(i == matcher.start(j) && i != matcher.end(j)){
						System.out.println("(" + str);
					}
				}
				for (int j = 1; j <= count; j++) {
					if(i + 1 != matcher.start(j) && i + 1 == matcher.end(j)){
						System.out.println(str + ")");
					}
				}
			}
		}else{
			System.out.println("不通过！");
		}
		
		
	}
}
