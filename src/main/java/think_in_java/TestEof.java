package think_in_java;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * 一个字节一个字节的读
 * @author dell
 *
 */
public class TestEof {
	public static void main(String[] args) {
		try {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("E:\\mye_workspace\\java_300\\src\\thiniinjava\\TestEof.java")));
			while(dis.available() != 0) //返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数。
				System.out.println((char)dis.readByte());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
