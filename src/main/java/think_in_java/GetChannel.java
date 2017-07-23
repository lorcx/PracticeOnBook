package think_in_java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio 通道  比久io速度更快
 * 我们并没有直接和通道进行交互，只与缓冲区有交互，通道要么向缓冲区发送数据，要么获得数据
 * 只有 FileOutputStream，RandomAccessFile，FileInputStream能获得
 * 
 * @author lx
 *
 */
public class GetChannel {
	static int BSIZE = 1024;
	public static void main(String[] args) {
		try {
			FileChannel fc = new FileOutputStream("E:\\1.txt").getChannel();//获取通道
			fc.write(ByteBuffer.wrap("some text".getBytes()));
			fc.close();
			
			fc = new RandomAccessFile("E:\\1.txt", "rw").getChannel();//rw 可读可写
			fc.position(BSIZE);//移动到指定位置
			fc.write(ByteBuffer.wrap("some text".getBytes())); //wrap 将 byte 数组包装到缓冲区中
			fc.close();
			
			fc = new FileInputStream("E:\\1.txt").getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(BSIZE);//创建字节缓冲区
			fc.read(buffer);
			buffer.flip();
			
			while (buffer.hasRemaining()) {
				System.out.println((char)buffer.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
