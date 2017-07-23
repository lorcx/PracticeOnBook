package think_in_java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * nio 转换数据
 * @author lx
 *
 */
public class BuffToText {
	static int BSIZE = 1024;//1K
	public static void main(String[] args) {
		try {
			FileChannel fc = new FileOutputStream("E:\\2.txt").getChannel();
			fc.write(ByteBuffer.wrap("some text".getBytes()));
			fc.close();  //关闭并刷新
			
			fc = new FileInputStream("E:\\2.txt").getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
			fc.read(buffer);
			buffer.flip();//反转缓冲区，在管道没有关闭的情况下，将数据从一个地方移到另一个地方，就要用这个方法。
			System.out.println(buffer.asCharBuffer());//转成char缓冲区
			
			buffer.rewind();//重绕此缓冲区
			String encoding = System.getProperty("file.encoding");
			System.out.println("encoding:" + encoding + ":" + Charset.forName(encoding).decode(buffer));// forname 返回指定 charset decode解码
			
			fc = new FileOutputStream("E:\\2.txt").getChannel();
			fc.write(ByteBuffer.wrap("some text".getBytes("UTF-16BE")));
			fc.close();
			
			fc = new FileInputStream("E:\\2.txt").getChannel();
			buffer.clear();//清空
			fc.read(buffer);
			buffer.flip();//反转
			System.out.println(buffer.asCharBuffer());
			
			fc = new FileOutputStream("E:\\2.txt").getChannel();
			buffer = ByteBuffer.allocate(24);
			buffer.asCharBuffer().put("some text");//另一种加入的方式
			fc.write(buffer);
			fc.close();
			
			fc = new FileInputStream("E:\\2.txt").getChannel();
			buffer.clear();
			fc.read(buffer);
			buffer.flip();
			System.out.println(buffer.asCharBuffer());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
