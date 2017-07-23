package think_in_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 老的容器
 * @author lx
 *
 */
public class Enumerations {

	public static void main(String[] args) {
		Vector<String> vt = new Vector<String>();
		Enumeration<String> et = vt.elements();
		while(et.hasMoreElements()){
			et.nextElement();
		}
		et = Collections.enumeration(new ArrayList<String>());
	}
	
}
