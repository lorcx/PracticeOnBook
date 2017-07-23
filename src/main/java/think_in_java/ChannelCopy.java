package think_in_java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用通道操作简单的文件复制
 * @author lx
 *
 */

@SuppressWarnings("all")
public class ChannelCopy {
	static int BSIZE = 1024;//1K
	public static void main(String[] args) {
		String file1 = "E:\\1.txt";
		String file2 = "E:\\2.txt";
		try {
			FileChannel in = new FileInputStream(file1).getChannel(),
						 out = new FileOutputStream(file2).getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(BSIZE);//创建缓冲区
			while(in.read(buffer) != -1){//将数据读取到缓冲区中
				buffer.flip();//写入之前必须要执行的
				out.write(buffer);
				buffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
