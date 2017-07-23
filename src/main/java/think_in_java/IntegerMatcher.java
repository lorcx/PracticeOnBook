package think_in_java;

import java.util.Arrays;


/**
 * 正则表达式
 * @author dell
 *
 */
public class IntegerMatcher {
	private static String str = "there is are sdffd,ewrwe ....ew  ewr sdvxcvwe ew wdcsfcw e";

	private static String[] split(String regex){
		return str.split(regex);
	}
	
	public static void main(String[] args) {
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("1234".matches("\\d+"));
		System.out.println("+911".matches("(-|\\+)?\\d+"));// +需要转义
		//String regex = " ";
//		String regex = ",";
//		String regex = "\\w?";
		String regex = "n\\w";
		System.out.println(Arrays.toString(split(regex)));
	}
	
	
}
