package think_in_java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *正则reset使用，将现有的mather对象应用到一个新的字符序列
 * @author dell
 *
 */
public class regularReset {
	public static void main(String[] args) {
		Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
						//构建matcher对象                                                                           要验证的字符串					
		while (m.find()) {
			System.out.print(m.group()+" ");
		}
		System.out.println();
		
		m.reset("fix the rig with rags");
		while(m.find()){
			System.out.print(m.group()+" ");
		}
		
	}
	
}
