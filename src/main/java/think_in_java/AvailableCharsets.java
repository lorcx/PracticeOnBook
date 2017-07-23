package think_in_java;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * nio报数据编码成不同类型的字符集
 * @author lx
 *
 */
public class AvailableCharsets {
	public static void main(String[] args) {
		SortedMap<String, Charset> map = Charset.availableCharsets();//返回jvm可用的charset
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String cName = it.next();
			System.out.print(cName);
			Iterator it1 = map.get(cName).aliases().iterator();//返回包含此 charset 各个别名的集合。
			if(it1.hasNext())
				System.out.print(" : ");
			while(it1.hasNext()){
				System.out.print(it1.next());
				if(it1.hasNext())
					System.out.print(",");
			}
			System.out.println();
		}
	}
}
