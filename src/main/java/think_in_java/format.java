package think_in_java;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * 格式化输出
 * @author dell
 *
 */
public class format {
	
	private String name;
	private Formatter matter;
	private double d = 33;
	
	public format(String name,Formatter matter) {
		this.name = name;
		this.matter = matter;
	}

	public Formatter move(int x , int y ){
		return matter.format("%s,%d,%d",name,x,y);
	}
	
	public Formatter toT(){
		return matter.format("%.2f",d);
	}
	
	public static void main(String[] args) {
		PrintStream ps = System.out;
		format f = new format("abcd", new Formatter(ps));
		f.move(3, 5).toString();
		System.out.println();
		f.toT().toString();
		
	}
	
	
}
