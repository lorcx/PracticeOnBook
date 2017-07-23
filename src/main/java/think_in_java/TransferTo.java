package think_in_java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * nio TransferTo
 * 用通道操作简单的文件复制
 * 与从此通道读取并将内容写入目标通道的简单循环语句相比，此方法可能高效得多。
 * @author lx
 *
 */

@SuppressWarnings("all")
public class TransferTo {
	static int BSIZE = 1024;//1K
	public static void main(String[] args) {
		String file1 = "E:\\1.txt";
		String file2 = "E:\\2.txt";
		try {//直接将2个通道连接起来
			FileChannel in = new FileInputStream(file1).getChannel(),
						 out = new FileOutputStream(file2).getChannel();
			in.transferTo(0, in.size(), out);//将此通道的文件直接传给写入通道
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
