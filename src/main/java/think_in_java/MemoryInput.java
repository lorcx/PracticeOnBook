package think_in_java;

import java.io.IOException;
import java.io.StringReader;

/**
 * 从内存输入
 * @author dell
 *
 */
public class MemoryInput {
	public static void main(String[] args) {
		StringReader reader = new StringReader(BufferedInputFile.read("E:\\mye_workspace\\java_300\\src\\thiniinjava\\MemoryInput.java"));
		int c;
		try {
			while((c = reader.read()) != -1){
				System.out.println(c);
//				System.out.println((char)c);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
