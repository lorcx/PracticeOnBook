package think_in_java;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * 格式化内存输出
 * @author dell
 *
 */
public class FormatMemoryInput {
	public static void main(String[] args) {
		DataInputStream dis = new DataInputStream(
								new ByteArrayInputStream(
									BufferedInputFile.read("E:\\mye_workspace\\java_300\\src\\thiniinjava\\FormatMemoryInput.java").getBytes()
									));
			try {
				while(true)
					System.out.println(dis.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
