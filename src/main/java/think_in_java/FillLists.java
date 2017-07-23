package think_in_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 容器填充
 * @author lx
 *
 */
public class FillLists {
	public static void main(String[] args) {
		//第一种
		List<FillLists> list = new ArrayList<FillLists>(
					Collections.nCopies(4, new FillLists())
				);
		System.out.println(list.toString());
		
//		list = new ArrayList<FillLists>(5);// 第二中填充会失效
		
		//第二种
		Collections.fill(list, new FillLists());//只能替换list中的值，不能新增
		
		System.out.println(list.toString());
		
	}
}
