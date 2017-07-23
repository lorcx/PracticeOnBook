package think_in_java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular：正则
 * Expression：表达式
 * @author dell
 *
 */
public class TestRegularExpression {
	//自己创建正则表达式
	public static void main(String[] args) {
		if(args.length < 2){
			System.exit(0);
		}
		for (String regex : args) {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(args[0]);
			while(m.find()){
				System.out.println(m.group());
			}
//			System.out.println(m.matches());//是否整个匹配
			
			System.out.println(m.lookingAt());//是否局部匹配
		}
		
	}
}
