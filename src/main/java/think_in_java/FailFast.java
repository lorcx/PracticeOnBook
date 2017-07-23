package think_in_java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * 快速报错 java容器自带的
 * 如果在操作一个容器时，有另一个进程操作这个容器，他就会快速报错。
 * 
 * 创建迭代器后又向容器中添加了元素，导致容器的状态不同，报快速报错。
 * 应该在添加元素后再创建迭代器。
 * @author lx
 *
 */
public class FailFast {

	 public static void main(String[] args) {
		Collection<String> c = new ArrayList<String>();
		Iterator<String> it = c.iterator();
		c.add("test");
		try {
			String msg = it.next();
		} catch (ConcurrentModificationException e) {
			System.out.println(e);
		}
	}
}
