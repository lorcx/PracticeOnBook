package think_in_java;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 视图缓冲
 * 可以让我们通过特定的基本数据类型查看底层的bytebuff
 * @author lx
 *
 */
public class IntBufferDemo {
	public static final int size = 1024;
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(size);//创建缓冲区
		IntBuffer intBuffer = buffer.asIntBuffer();
		intBuffer.put(new int[]{23,4,5,32,76,12,57,43});
		System.out.println(intBuffer.get(3));
		intBuffer.put(3,1024);
		intBuffer.flip();//准备
		while(intBuffer.hasRemaining()){
			System.out.println(intBuffer.get());
		}
	}
}
