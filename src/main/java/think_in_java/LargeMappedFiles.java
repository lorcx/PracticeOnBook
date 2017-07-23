package think_in_java;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存文件映射,大文件不能直接放到内存中
 * @author lx
 *
 */
public class LargeMappedFiles {
	static int length = 0x8FFFFFF;//128MB
	public static void main(String[] args) {
		try {
			MappedByteBuffer out = new RandomAccessFile("E:\\1.txt", "rw").getChannel().
										map(FileChannel.MapMode.READ_WRITE, 0,length);
			for (int i = 0; i < length; i++) {
				out.put((byte)'x');		
			}
			System.out.println("finished writing");
			for (int i = length/2; i < length/2 + 6; i++) {
				System.out.println((char)out.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
