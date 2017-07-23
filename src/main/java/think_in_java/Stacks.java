package think_in_java;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 老容器不应该用
 * @author lx
 *
 */
public class Stacks {
	
	public static void main(String[] args) {
		Stack<String> st = new Stack<String>();
		for (Moth val : Moth.values()) {
			st.push(val.toString());
		}
		System.out.println(st);
		st.addElement("the last");
		System.out.println(st.elementAt(5));
		while(!st.empty()){
			System.out.println(st.pop());
		}
		System.out.println(st);
		LinkedList<String> ll = new LinkedList<String>();
		for (Moth val : Moth.values()) {
			ll.addFirst(val.toString());
		}
		System.out.println(ll);
		while(!ll.isEmpty()){
			System.out.println(ll.removeFirst());
		}
		System.out.println(ll);
	}
	
}

enum Moth{
	JAUNARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SETEMBER,OCTOBER,NOVEMBER
}