package think_in_java;

import java.util.Arrays;
import java.util.Collection;

/**
 * 格式化输出容器
 * @author lx
 *
 */
public class PPrint {
	/**
	 * 格式化
	 * @param c
	 * @return
	 */
	public static String printFormt(Collection<?> c){
		StringBuilder sb = new StringBuilder();
		if(c.size() == 0)
			sb.append("[]");
		sb.append("[");
		for (Object item : c) {
			if(c.size() != 1)
				sb.append("\n");
			sb.append(item);
		}
		if(c.size() != 1)
			sb.append("\n");
		sb.append("]");
		return sb.toString();
	}
	
	public static void pprint(Collection<?> c){
		System.out.println(printFormt(c));
	}
	
	public static void pprint(Object[] c){
		System.out.println(printFormt(Arrays.asList(c)));
	}
}
