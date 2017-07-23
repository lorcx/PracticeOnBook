package think_in_java;

/**
 * printf使用
 * @author dell
 *
 */
public class PrintFTest {
	public static void main(String[] args) {
		int x = 5;
		double y = 5.34245;
		System.out.println("Row 1:["+ x +" "+ y +"]");
		System.out.format("Row 1:[%d,%f]\n",x,y);
	}
}
