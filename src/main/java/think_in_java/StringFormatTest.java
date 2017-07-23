package think_in_java;

public class StringFormatTest {
	public static void main(String[] args) {
		System.out.println(String.format("%05x", 10));//前边补零，保留5位数
		System.out.println(String.format("%02x", 10));//前边补零，保留2位数
		System.out.println(String.format("%2x", 10));
		for (int i = 0; i < 16; i++) {
			System.out.println(i % 16);
		}
	}
}
