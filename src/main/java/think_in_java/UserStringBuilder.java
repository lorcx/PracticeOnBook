package think_in_java;

import java.util.Random;

/**
 * 自定义的stringBuilder
 * @author dell
 *
 */
public class UserStringBuilder {
	private Random rn = new Random(47);//47代表的随机多少个
	/**
	 * 在toString中使用循环最好自己从写
	 */
	public String toString(){
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < 25; i++) {
			result.append(rn.nextInt(100));//100代表随机的范围
			result.append(", ");
		}
		result.delete(result.length() - 2, result.length());
		result.append("]");
		return result.toString();
	}
	
	public static void main(String[] args) {
		UserStringBuilder usb = new UserStringBuilder();
		System.out.println(usb.toString());
	}
}
