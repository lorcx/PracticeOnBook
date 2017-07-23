package think_in_java;
/**
 * 正则表达式
 * @author dell
 *
 */
public class Rudolph {
	public static void main(String[] args) {
		for(String partten : new String[]{"Rudolph","[r|R]udolph","[r|R][aeiou][a-z]ol.*","R.*"}){
			System.out.println("Rudolph".matches(partten));
		}
		
	}
}
