package think_in_java;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 *  scanner 
 * @author dell
 *
 */
public class RandomWords implements Readable {
	private int count;
	public RandomWords(int c){
		this.count = c;
	}
	@Override
	public int read(CharBuffer cb) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(new RandomWords(6));
		sc.next();
	}

}
