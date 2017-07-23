package think_in_java;

/**
 *  Generic:一般的
 * @author dell
 *
 */
public class GenericClassReferences {
	public static void main(String[] args) {
		Class<? extends Number> c = init.class; //？表示任何的
	}
}

class init extends Number {
	public int intValue() {
		return 0;
	}
	public long longValue() {
		return 0;
	}
	public float floatValue() {
		return 0;
	}
	public double doubleValue() {
		return 0;
	}
}